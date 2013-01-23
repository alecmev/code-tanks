import com.a.a.h;
import org.apache.log4j.Logger;

public final class LocalTestRunner
{
  public static void main(String[] paramArrayOfString)
  {
    Logger.getRootLogger().removeAllAppenders();
    new h(new String[] { "-render-to-screen=true", "-render-to-screen-scale=0.75", "-debug=true", "-base-adapter-port=31000", "-p1-name=A", "-p2-name=B", "MyStrategy.cs", "MyStrategy.cs" }).run();
  }
}