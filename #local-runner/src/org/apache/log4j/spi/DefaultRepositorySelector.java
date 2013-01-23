package org.apache.log4j.spi;

public class DefaultRepositorySelector
  implements RepositorySelector
{
  final LoggerRepository repository;

  public DefaultRepositorySelector(LoggerRepository paramLoggerRepository)
  {
    this.repository = paramLoggerRepository;
  }

  public LoggerRepository getLoggerRepository()
  {
    return this.repository;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.spi.DefaultRepositorySelector
 * JD-Core Version:    0.6.2
 */