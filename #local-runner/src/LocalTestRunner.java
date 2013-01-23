/*   */ import com.a.a.h;
/*   */ import org.apache.log4j.Logger;
/*   */ 
/*   */ public final class LocalTestRunner
/*   */ {
/*   */   public static void main(String[] paramArrayOfString)
/*   */   {
/* 8 */     Logger.getRootLogger().removeAllAppenders();
/* 9 */     new h(new String[] { "-render-to-screen=true", "-render-to-screen-scale=0.75", "-debug=true", "-base-adapter-port=31000", "-p1-name=A", "-p2-name=B", "MyStrategy.cs", "MyStrategy.cs" }).run();
/*   */   }
/*   */ }

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     LocalTestRunner
 * JD-Core Version:    0.6.2
 */