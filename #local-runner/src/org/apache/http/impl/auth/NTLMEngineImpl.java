package org.apache.http.impl.auth;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.util.EncodingUtils;

final class NTLMEngineImpl
  implements NTLMEngine
{
  private static final SecureRandom RND_GEN;
  private String credentialCharset = "ASCII";
  private static byte[] SIGNATURE;

  String getType1Message(String paramString1, String paramString2)
    throws NTLMEngineException
  {
    return new Type1Message(paramString2, paramString1).getResponse();
  }

  String getType3Message(String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte1, int paramInt, String paramString5, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    return new Type3Message(paramString4, paramString3, paramString1, paramString2, paramArrayOfByte1, paramInt, paramString5, paramArrayOfByte2).getResponse();
  }

  private static String stripDotSuffix(String paramString)
  {
    int i = paramString.indexOf(".");
    if (i != -1)
      return paramString.substring(0, i);
    return paramString;
  }

  private static String convertHost(String paramString)
  {
    return stripDotSuffix(paramString);
  }

  private static String convertDomain(String paramString)
  {
    return stripDotSuffix(paramString);
  }

  private static int readULong(byte[] paramArrayOfByte, int paramInt)
    throws NTLMEngineException
  {
    if (paramArrayOfByte.length < paramInt + 4)
      throw new NTLMEngineException("NTLM authentication - buffer too small for DWORD");
    return paramArrayOfByte[paramInt] & 0xFF | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 16 | (paramArrayOfByte[(paramInt + 3)] & 0xFF) << 24;
  }

  private static int readUShort(byte[] paramArrayOfByte, int paramInt)
    throws NTLMEngineException
  {
    if (paramArrayOfByte.length < paramInt + 2)
      throw new NTLMEngineException("NTLM authentication - buffer too small for WORD");
    return paramArrayOfByte[paramInt] & 0xFF | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8;
  }

  private static byte[] readSecurityBuffer(byte[] paramArrayOfByte, int paramInt)
    throws NTLMEngineException
  {
    int i = readUShort(paramArrayOfByte, paramInt);
    int j = readULong(paramArrayOfByte, paramInt + 4);
    if (paramArrayOfByte.length < j + i)
      throw new NTLMEngineException("NTLM authentication - buffer too small for data item");
    byte[] arrayOfByte = new byte[i];
    System.arraycopy(paramArrayOfByte, j, arrayOfByte, 0, i);
    return arrayOfByte;
  }

  private static byte[] makeRandomChallenge()
    throws NTLMEngineException
  {
    if (RND_GEN == null)
      throw new NTLMEngineException("Random generator not available");
    byte[] arrayOfByte = new byte[8];
    synchronized (RND_GEN)
    {
      RND_GEN.nextBytes(arrayOfByte);
    }
    return arrayOfByte;
  }

  private static byte[] makeNTLM2RandomChallenge()
    throws NTLMEngineException
  {
    if (RND_GEN == null)
      throw new NTLMEngineException("Random generator not available");
    byte[] arrayOfByte = new byte[24];
    synchronized (RND_GEN)
    {
      RND_GEN.nextBytes(arrayOfByte);
    }
    Arrays.fill(arrayOfByte, 8, 24, (byte)0);
    return arrayOfByte;
  }

  static byte[] getLMResponse(String paramString, byte[] paramArrayOfByte)
    throws NTLMEngineException
  {
    byte[] arrayOfByte = lmHash(paramString);
    return lmResponse(arrayOfByte, paramArrayOfByte);
  }

  static byte[] getNTLMResponse(String paramString, byte[] paramArrayOfByte)
    throws NTLMEngineException
  {
    byte[] arrayOfByte = ntlmHash(paramString);
    return lmResponse(arrayOfByte, paramArrayOfByte);
  }

  static byte[] getNTLMv2Response(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws NTLMEngineException
  {
    byte[] arrayOfByte1 = ntlmv2Hash(paramString1, paramString2, paramString3);
    byte[] arrayOfByte2 = createBlob(paramArrayOfByte2, paramArrayOfByte3);
    return lmv2Response(arrayOfByte1, paramArrayOfByte1, arrayOfByte2);
  }

  static byte[] getLMv2Response(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    byte[] arrayOfByte = ntlmv2Hash(paramString1, paramString2, paramString3);
    return lmv2Response(arrayOfByte, paramArrayOfByte1, paramArrayOfByte2);
  }

  static byte[] getNTLM2SessionResponse(String paramString, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    try
    {
      byte[] arrayOfByte1 = ntlmHash(paramString);
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte1);
      localMessageDigest.update(paramArrayOfByte2);
      byte[] arrayOfByte2 = localMessageDigest.digest();
      byte[] arrayOfByte3 = new byte[8];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 0, 8);
      return lmResponse(arrayOfByte1, arrayOfByte3);
    }
    catch (Exception localException)
    {
      if ((localException instanceof NTLMEngineException))
        throw ((NTLMEngineException)localException);
      throw new NTLMEngineException(localException.getMessage(), localException);
    }
  }

  private static byte[] lmHash(String paramString)
    throws NTLMEngineException
  {
    try
    {
      byte[] arrayOfByte1 = paramString.toUpperCase().getBytes("US-ASCII");
      int i = Math.min(arrayOfByte1.length, 14);
      byte[] arrayOfByte2 = new byte[14];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
      Key localKey1 = createDESKey(arrayOfByte2, 0);
      Key localKey2 = createDESKey(arrayOfByte2, 7);
      byte[] arrayOfByte3 = "KGS!@#$%".getBytes("US-ASCII");
      Cipher localCipher = Cipher.getInstance("DES/ECB/NoPadding");
      localCipher.init(1, localKey1);
      byte[] arrayOfByte4 = localCipher.doFinal(arrayOfByte3);
      localCipher.init(1, localKey2);
      byte[] arrayOfByte5 = localCipher.doFinal(arrayOfByte3);
      byte[] arrayOfByte6 = new byte[16];
      System.arraycopy(arrayOfByte4, 0, arrayOfByte6, 0, 8);
      System.arraycopy(arrayOfByte5, 0, arrayOfByte6, 8, 8);
      return arrayOfByte6;
    }
    catch (Exception localException)
    {
      throw new NTLMEngineException(localException.getMessage(), localException);
    }
  }

  private static byte[] ntlmHash(String paramString)
    throws NTLMEngineException
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("UnicodeLittleUnmarked");
      MD4 localMD4 = new MD4();
      localMD4.update(arrayOfByte);
      return localMD4.getOutput();
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new NTLMEngineException("Unicode not supported: " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
    }
  }

  private static byte[] ntlmv2Hash(String paramString1, String paramString2, String paramString3)
    throws NTLMEngineException
  {
    try
    {
      byte[] arrayOfByte = ntlmHash(paramString3);
      HMACMD5 localHMACMD5 = new HMACMD5(arrayOfByte);
      localHMACMD5.update(paramString2.toUpperCase().getBytes("UnicodeLittleUnmarked"));
      localHMACMD5.update(paramString1.getBytes("UnicodeLittleUnmarked"));
      return localHMACMD5.getOutput();
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new NTLMEngineException("Unicode not supported! " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
    }
  }

  private static byte[] lmResponse(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    try
    {
      byte[] arrayOfByte1 = new byte[21];
      System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, 0, 16);
      Key localKey1 = createDESKey(arrayOfByte1, 0);
      Key localKey2 = createDESKey(arrayOfByte1, 7);
      Key localKey3 = createDESKey(arrayOfByte1, 14);
      Cipher localCipher = Cipher.getInstance("DES/ECB/NoPadding");
      localCipher.init(1, localKey1);
      byte[] arrayOfByte2 = localCipher.doFinal(paramArrayOfByte2);
      localCipher.init(1, localKey2);
      byte[] arrayOfByte3 = localCipher.doFinal(paramArrayOfByte2);
      localCipher.init(1, localKey3);
      byte[] arrayOfByte4 = localCipher.doFinal(paramArrayOfByte2);
      byte[] arrayOfByte5 = new byte[24];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte5, 0, 8);
      System.arraycopy(arrayOfByte3, 0, arrayOfByte5, 8, 8);
      System.arraycopy(arrayOfByte4, 0, arrayOfByte5, 16, 8);
      return arrayOfByte5;
    }
    catch (Exception localException)
    {
      throw new NTLMEngineException(localException.getMessage(), localException);
    }
  }

  private static byte[] lmv2Response(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws NTLMEngineException
  {
    HMACMD5 localHMACMD5 = new HMACMD5(paramArrayOfByte1);
    localHMACMD5.update(paramArrayOfByte2);
    localHMACMD5.update(paramArrayOfByte3);
    byte[] arrayOfByte1 = localHMACMD5.getOutput();
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length + paramArrayOfByte3.length];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte2, arrayOfByte1.length, paramArrayOfByte3.length);
    return arrayOfByte2;
  }

  private static byte[] createBlob(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte1 = { 1, 1, 0, 0 };
    byte[] arrayOfByte2 = { 0, 0, 0, 0 };
    byte[] arrayOfByte3 = { 0, 0, 0, 0 };
    long l = System.currentTimeMillis();
    l += 11644473600000L;
    l *= 10000L;
    byte[] arrayOfByte4 = new byte[8];
    for (int i = 0; i < 8; i++)
    {
      arrayOfByte4[i] = ((byte)(int)l);
      l >>>= 8;
    }
    byte[] arrayOfByte5 = new byte[arrayOfByte1.length + arrayOfByte2.length + arrayOfByte4.length + 8 + arrayOfByte3.length + paramArrayOfByte2.length];
    int j = 0;
    System.arraycopy(arrayOfByte1, 0, arrayOfByte5, j, arrayOfByte1.length);
    j += arrayOfByte1.length;
    System.arraycopy(arrayOfByte2, 0, arrayOfByte5, j, arrayOfByte2.length);
    j += arrayOfByte2.length;
    System.arraycopy(arrayOfByte4, 0, arrayOfByte5, j, arrayOfByte4.length);
    j += arrayOfByte4.length;
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte5, j, 8);
    j += 8;
    System.arraycopy(arrayOfByte3, 0, arrayOfByte5, j, arrayOfByte3.length);
    j += arrayOfByte3.length;
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte5, j, paramArrayOfByte2.length);
    return arrayOfByte5;
  }

  private static Key createDESKey(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte1 = new byte[7];
    System.arraycopy(paramArrayOfByte, paramInt, arrayOfByte1, 0, 7);
    byte[] arrayOfByte2 = new byte[8];
    arrayOfByte2[0] = arrayOfByte1[0];
    arrayOfByte2[1] = ((byte)(arrayOfByte1[0] << 7 | (arrayOfByte1[1] & 0xFF) >>> 1));
    arrayOfByte2[2] = ((byte)(arrayOfByte1[1] << 6 | (arrayOfByte1[2] & 0xFF) >>> 2));
    arrayOfByte2[3] = ((byte)(arrayOfByte1[2] << 5 | (arrayOfByte1[3] & 0xFF) >>> 3));
    arrayOfByte2[4] = ((byte)(arrayOfByte1[3] << 4 | (arrayOfByte1[4] & 0xFF) >>> 4));
    arrayOfByte2[5] = ((byte)(arrayOfByte1[4] << 3 | (arrayOfByte1[5] & 0xFF) >>> 5));
    arrayOfByte2[6] = ((byte)(arrayOfByte1[5] << 2 | (arrayOfByte1[6] & 0xFF) >>> 6));
    arrayOfByte2[7] = ((byte)(arrayOfByte1[6] << 1));
    oddParity(arrayOfByte2);
    return new SecretKeySpec(arrayOfByte2, "DES");
  }

  private static void oddParity(byte[] paramArrayOfByte)
  {
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      int j = paramArrayOfByte[i];
      int k = ((j >>> 7 ^ j >>> 6 ^ j >>> 5 ^ j >>> 4 ^ j >>> 3 ^ j >>> 2 ^ j >>> 1) & 0x1) == 0 ? 1 : 0;
      if (k != 0)
      {
        int tmp58_57 = i;
        paramArrayOfByte[tmp58_57] = ((byte)(paramArrayOfByte[tmp58_57] | 0x1));
      }
      else
      {
        int tmp69_68 = i;
        paramArrayOfByte[tmp69_68] = ((byte)(paramArrayOfByte[tmp69_68] & 0xFFFFFFFE));
      }
    }
  }

  static void writeULong(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 & 0xFF));
    paramArrayOfByte[(paramInt2 + 1)] = ((byte)(paramInt1 >> 8 & 0xFF));
    paramArrayOfByte[(paramInt2 + 2)] = ((byte)(paramInt1 >> 16 & 0xFF));
    paramArrayOfByte[(paramInt2 + 3)] = ((byte)(paramInt1 >> 24 & 0xFF));
  }

  static int F(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 & paramInt2 | (paramInt1 ^ 0xFFFFFFFF) & paramInt3;
  }

  static int G(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 & paramInt2 | paramInt1 & paramInt3 | paramInt2 & paramInt3;
  }

  static int H(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 ^ paramInt2 ^ paramInt3;
  }

  static int rotintlft(int paramInt1, int paramInt2)
  {
    return paramInt1 << paramInt2 | paramInt1 >>> 32 - paramInt2;
  }

  public String generateType1Msg(String paramString1, String paramString2)
    throws NTLMEngineException
  {
    return getType1Message(paramString2, paramString1);
  }

  public String generateType3Msg(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws NTLMEngineException
  {
    Type2Message localType2Message = new Type2Message(paramString5);
    return getType3Message(paramString1, paramString2, paramString4, paramString3, localType2Message.getChallenge(), localType2Message.getFlags(), localType2Message.getTarget(), localType2Message.getTargetInfo());
  }

  static
  {
    Object localObject = null;
    try
    {
      localObject = SecureRandom.getInstance("SHA1PRNG");
    }
    catch (Exception localException)
    {
    }
    RND_GEN = (SecureRandom)localObject;
    localObject = EncodingUtils.getBytes("NTLMSSP", "ASCII");
    SIGNATURE = new byte[localObject.length + 1];
    System.arraycopy(localObject, 0, SIGNATURE, 0, localObject.length);
    SIGNATURE[localObject.length] = 0;
  }

  static class HMACMD5
  {
    protected byte[] ipad;
    protected byte[] opad;
    protected MessageDigest md5;

    HMACMD5(byte[] paramArrayOfByte)
      throws NTLMEngineException
    {
      try
      {
        this.md5 = MessageDigest.getInstance("MD5");
      }
      catch (Exception localException)
      {
        throw new NTLMEngineException("Error getting md5 message digest implementation: " + localException.getMessage(), localException);
      }
      this.ipad = new byte[64];
      this.opad = new byte[64];
      int i = paramArrayOfByte.length;
      if (i > 64)
      {
        this.md5.update(paramArrayOfByte);
        paramArrayOfByte = this.md5.digest();
        i = paramArrayOfByte.length;
      }
      for (int j = 0; j < i; j++)
      {
        this.ipad[j] = ((byte)(paramArrayOfByte[j] ^ 0x36));
        this.opad[j] = ((byte)(paramArrayOfByte[j] ^ 0x5C));
      }
      while (j < 64)
      {
        this.ipad[j] = 54;
        this.opad[j] = 92;
        j++;
      }
      this.md5.reset();
      this.md5.update(this.ipad);
    }

    byte[] getOutput()
    {
      byte[] arrayOfByte = this.md5.digest();
      this.md5.update(this.opad);
      return this.md5.digest(arrayOfByte);
    }

    void update(byte[] paramArrayOfByte)
    {
      this.md5.update(paramArrayOfByte);
    }
  }

  static class MD4
  {
    protected int A = 1732584193;
    protected int B = -271733879;
    protected int C = -1732584194;
    protected int D = 271733878;
    protected long count = 0L;
    protected byte[] dataBuffer = new byte[64];

    void update(byte[] paramArrayOfByte)
    {
      int i = (int)(this.count & 0x3F);
      int j = 0;
      int k;
      while (paramArrayOfByte.length - j + i >= this.dataBuffer.length)
      {
        k = this.dataBuffer.length - i;
        System.arraycopy(paramArrayOfByte, j, this.dataBuffer, i, k);
        this.count += k;
        i = 0;
        j += k;
        processBuffer();
      }
      if (j < paramArrayOfByte.length)
      {
        k = paramArrayOfByte.length - j;
        System.arraycopy(paramArrayOfByte, j, this.dataBuffer, i, k);
        this.count += k;
        i += k;
      }
    }

    byte[] getOutput()
    {
      int i = (int)(this.count & 0x3F);
      int j = i < 56 ? 56 - i : 120 - i;
      byte[] arrayOfByte1 = new byte[j + 8];
      arrayOfByte1[0] = -128;
      for (int k = 0; k < 8; k++)
        arrayOfByte1[(j + k)] = ((byte)(int)(this.count * 8L >>> 8 * k));
      update(arrayOfByte1);
      byte[] arrayOfByte2 = new byte[16];
      NTLMEngineImpl.writeULong(arrayOfByte2, this.A, 0);
      NTLMEngineImpl.writeULong(arrayOfByte2, this.B, 4);
      NTLMEngineImpl.writeULong(arrayOfByte2, this.C, 8);
      NTLMEngineImpl.writeULong(arrayOfByte2, this.D, 12);
      return arrayOfByte2;
    }

    protected void processBuffer()
    {
      int[] arrayOfInt = new int[16];
      for (int i = 0; i < 16; i++)
        arrayOfInt[i] = ((this.dataBuffer[(i * 4)] & 0xFF) + ((this.dataBuffer[(i * 4 + 1)] & 0xFF) << 8) + ((this.dataBuffer[(i * 4 + 2)] & 0xFF) << 16) + ((this.dataBuffer[(i * 4 + 3)] & 0xFF) << 24));
      i = this.A;
      int j = this.B;
      int k = this.C;
      int m = this.D;
      round1(arrayOfInt);
      round2(arrayOfInt);
      round3(arrayOfInt);
      this.A += i;
      this.B += j;
      this.C += k;
      this.D += m;
    }

    protected void round1(int[] paramArrayOfInt)
    {
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[0], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[1], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[2], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[3], 19);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[4], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[5], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[6], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[7], 19);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[8], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[9], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[10], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[11], 19);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[12], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[13], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[14], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[15], 19);
    }

    protected void round2(int[] paramArrayOfInt)
    {
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[0] + 1518500249, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[4] + 1518500249, 5);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[8] + 1518500249, 9);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[12] + 1518500249, 13);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[1] + 1518500249, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[5] + 1518500249, 5);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[9] + 1518500249, 9);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[13] + 1518500249, 13);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[2] + 1518500249, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[6] + 1518500249, 5);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[10] + 1518500249, 9);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[14] + 1518500249, 13);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[3] + 1518500249, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[7] + 1518500249, 5);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[11] + 1518500249, 9);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[15] + 1518500249, 13);
    }

    protected void round3(int[] paramArrayOfInt)
    {
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[0] + 1859775393, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[8] + 1859775393, 9);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[4] + 1859775393, 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[12] + 1859775393, 15);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[2] + 1859775393, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[10] + 1859775393, 9);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[6] + 1859775393, 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[14] + 1859775393, 15);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[1] + 1859775393, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[9] + 1859775393, 9);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[5] + 1859775393, 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[13] + 1859775393, 15);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[3] + 1859775393, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[11] + 1859775393, 9);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[7] + 1859775393, 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[15] + 1859775393, 15);
    }
  }

  static class Type3Message extends NTLMEngineImpl.NTLMMessage
  {
    protected int type2Flags;
    protected byte[] domainBytes;
    protected byte[] hostBytes;
    protected byte[] userBytes;
    protected byte[] lmResp;
    protected byte[] ntResp;

    Type3Message(String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte1, int paramInt, String paramString5, byte[] paramArrayOfByte2)
      throws NTLMEngineException
    {
      this.type2Flags = paramInt;
      paramString2 = NTLMEngineImpl.convertHost(paramString2);
      paramString1 = NTLMEngineImpl.convertDomain(paramString1);
      try
      {
        byte[] arrayOfByte;
        if ((paramArrayOfByte2 != null) && (paramString5 != null))
        {
          arrayOfByte = NTLMEngineImpl.access$600();
          this.ntResp = NTLMEngineImpl.getNTLMv2Response(paramString5, paramString3, paramString4, paramArrayOfByte1, arrayOfByte, paramArrayOfByte2);
          this.lmResp = NTLMEngineImpl.getLMv2Response(paramString5, paramString3, paramString4, paramArrayOfByte1, arrayOfByte);
        }
        else if ((paramInt & 0x80000) != 0)
        {
          arrayOfByte = NTLMEngineImpl.access$700();
          this.ntResp = NTLMEngineImpl.getNTLM2SessionResponse(paramString4, paramArrayOfByte1, arrayOfByte);
          this.lmResp = arrayOfByte;
        }
        else
        {
          this.ntResp = NTLMEngineImpl.getNTLMResponse(paramString4, paramArrayOfByte1);
          this.lmResp = NTLMEngineImpl.getLMResponse(paramString4, paramArrayOfByte1);
        }
      }
      catch (NTLMEngineException localNTLMEngineException)
      {
        this.ntResp = new byte[0];
        this.lmResp = NTLMEngineImpl.getLMResponse(paramString4, paramArrayOfByte1);
      }
      try
      {
        this.domainBytes = paramString1.toUpperCase().getBytes("UnicodeLittleUnmarked");
        this.hostBytes = paramString2.getBytes("UnicodeLittleUnmarked");
        this.userBytes = paramString3.getBytes("UnicodeLittleUnmarked");
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        throw new NTLMEngineException("Unicode not supported: " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
      }
    }

    String getResponse()
    {
      int i = this.ntResp.length;
      int j = this.lmResp.length;
      int k = this.domainBytes.length;
      int m = this.hostBytes.length;
      int n = this.userBytes.length;
      int i1 = 64;
      int i2 = i1 + j;
      int i3 = i2 + i;
      int i4 = i3 + k;
      int i5 = i4 + n;
      int i6 = i5 + m;
      int i7 = i6 + 0;
      prepareResponse(i7, 3);
      addUShort(j);
      addUShort(j);
      addULong(i1);
      addUShort(i);
      addUShort(i);
      addULong(i2);
      addUShort(k);
      addUShort(k);
      addULong(i3);
      addUShort(n);
      addUShort(n);
      addULong(i4);
      addUShort(m);
      addUShort(m);
      addULong(i5);
      addULong(0);
      addULong(i7);
      addULong(0x20000205 | this.type2Flags & 0x80000 | this.type2Flags & 0x10 | this.type2Flags & 0x20 | this.type2Flags & 0x40000000 | this.type2Flags & 0x8000);
      addBytes(this.lmResp);
      addBytes(this.ntResp);
      addBytes(this.domainBytes);
      addBytes(this.userBytes);
      addBytes(this.hostBytes);
      return super.getResponse();
    }
  }

  static class Type2Message extends NTLMEngineImpl.NTLMMessage
  {
    protected byte[] challenge = new byte[8];
    protected String target;
    protected byte[] targetInfo;
    protected int flags;

    Type2Message(String paramString)
      throws NTLMEngineException
    {
      super(2);
      readBytes(this.challenge, 24);
      this.flags = readULong(20);
      if ((this.flags & 0x1) == 0)
        throw new NTLMEngineException("NTLM type 2 message has flags that make no sense: " + Integer.toString(this.flags));
      this.target = null;
      byte[] arrayOfByte;
      if (getMessageLength() >= 20)
      {
        arrayOfByte = readSecurityBuffer(12);
        if (arrayOfByte.length != 0)
          try
          {
            this.target = new String(arrayOfByte, "UnicodeLittleUnmarked");
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException)
          {
            throw new NTLMEngineException(localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
          }
      }
      this.targetInfo = null;
      if (getMessageLength() >= 48)
      {
        arrayOfByte = readSecurityBuffer(40);
        if (arrayOfByte.length != 0)
          this.targetInfo = arrayOfByte;
      }
    }

    byte[] getChallenge()
    {
      return this.challenge;
    }

    String getTarget()
    {
      return this.target;
    }

    byte[] getTargetInfo()
    {
      return this.targetInfo;
    }

    int getFlags()
    {
      return this.flags;
    }
  }

  static class Type1Message extends NTLMEngineImpl.NTLMMessage
  {
    protected byte[] hostBytes;
    protected byte[] domainBytes;

    Type1Message(String paramString1, String paramString2)
      throws NTLMEngineException
    {
      try
      {
        paramString2 = NTLMEngineImpl.convertHost(paramString2);
        paramString1 = NTLMEngineImpl.convertDomain(paramString1);
        this.hostBytes = paramString2.getBytes("UnicodeLittleUnmarked");
        this.domainBytes = paramString1.toUpperCase().getBytes("UnicodeLittleUnmarked");
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        throw new NTLMEngineException("Unicode unsupported: " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
      }
    }

    String getResponse()
    {
      int i = 32 + this.hostBytes.length + this.domainBytes.length;
      prepareResponse(i, 1);
      addULong(537395765);
      addUShort(this.domainBytes.length);
      addUShort(this.domainBytes.length);
      addULong(this.hostBytes.length + 32);
      addUShort(this.hostBytes.length);
      addUShort(this.hostBytes.length);
      addULong(32);
      addBytes(this.hostBytes);
      addBytes(this.domainBytes);
      return super.getResponse();
    }
  }

  static class NTLMMessage
  {
    private byte[] messageContents = null;
    private int currentOutputPosition = 0;

    NTLMMessage()
    {
    }

    NTLMMessage(String paramString, int paramInt)
      throws NTLMEngineException
    {
      this.messageContents = Base64.decodeBase64(EncodingUtils.getBytes(paramString, "ASCII"));
      if (this.messageContents.length < NTLMEngineImpl.SIGNATURE.length)
        throw new NTLMEngineException("NTLM message decoding error - packet too short");
      for (int i = 0; i < NTLMEngineImpl.SIGNATURE.length; i++)
        if (this.messageContents[i] != NTLMEngineImpl.SIGNATURE[i])
          throw new NTLMEngineException("NTLM message expected - instead got unrecognized bytes");
      int j = readULong(NTLMEngineImpl.SIGNATURE.length);
      if (j != paramInt)
        throw new NTLMEngineException("NTLM type " + Integer.toString(paramInt) + " message expected - instead got type " + Integer.toString(j));
      this.currentOutputPosition = this.messageContents.length;
    }

    protected int getMessageLength()
    {
      return this.currentOutputPosition;
    }

    protected void readBytes(byte[] paramArrayOfByte, int paramInt)
      throws NTLMEngineException
    {
      if (this.messageContents.length < paramInt + paramArrayOfByte.length)
        throw new NTLMEngineException("NTLM: Message too short");
      System.arraycopy(this.messageContents, paramInt, paramArrayOfByte, 0, paramArrayOfByte.length);
    }

    protected int readULong(int paramInt)
      throws NTLMEngineException
    {
      return NTLMEngineImpl.readULong(this.messageContents, paramInt);
    }

    protected byte[] readSecurityBuffer(int paramInt)
      throws NTLMEngineException
    {
      return NTLMEngineImpl.readSecurityBuffer(this.messageContents, paramInt);
    }

    protected void prepareResponse(int paramInt1, int paramInt2)
    {
      this.messageContents = new byte[paramInt1];
      this.currentOutputPosition = 0;
      addBytes(NTLMEngineImpl.SIGNATURE);
      addULong(paramInt2);
    }

    protected void addByte(byte paramByte)
    {
      this.messageContents[this.currentOutputPosition] = paramByte;
      this.currentOutputPosition += 1;
    }

    protected void addBytes(byte[] paramArrayOfByte)
    {
      for (int i = 0; i < paramArrayOfByte.length; i++)
      {
        this.messageContents[this.currentOutputPosition] = paramArrayOfByte[i];
        this.currentOutputPosition += 1;
      }
    }

    protected void addUShort(int paramInt)
    {
      addByte((byte)(paramInt & 0xFF));
      addByte((byte)(paramInt >> 8 & 0xFF));
    }

    protected void addULong(int paramInt)
    {
      addByte((byte)(paramInt & 0xFF));
      addByte((byte)(paramInt >> 8 & 0xFF));
      addByte((byte)(paramInt >> 16 & 0xFF));
      addByte((byte)(paramInt >> 24 & 0xFF));
    }

    String getResponse()
    {
      byte[] arrayOfByte1;
      if (this.messageContents.length > this.currentOutputPosition)
      {
        byte[] arrayOfByte2 = new byte[this.currentOutputPosition];
        for (int i = 0; i < this.currentOutputPosition; i++)
          arrayOfByte2[i] = this.messageContents[i];
        arrayOfByte1 = arrayOfByte2;
      }
      else
      {
        arrayOfByte1 = this.messageContents;
      }
      return EncodingUtils.getAsciiString(Base64.encodeBase64(arrayOfByte1));
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.auth.NTLMEngineImpl
 * JD-Core Version:    0.6.2
 */