package de.gedoplan.service;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Service2 {

  private AtomicBoolean ok = new AtomicBoolean(true);

  public boolean isOk() {
    return this.ok.get();
  }

  public void setOk(boolean ok) {
    this.ok.set(ok);
  }

}
