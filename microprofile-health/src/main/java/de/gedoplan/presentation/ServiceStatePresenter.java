package de.gedoplan.presentation;

import de.gedoplan.service.Service1;
import de.gedoplan.service.Service2;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ServiceStatePresenter {

  @Inject
  Service1 service1;

  @Inject
  Service2 service2;

  public boolean isService1Ok() {
    return this.service1.isOk();
  }

  public void setService1Ok(boolean ok) {
    this.service1.setOk(ok);
  }

  public boolean isService2Ok() {
    return this.service2.isOk();
  }

  public void setService2Ok(boolean ok) {
    this.service2.setOk(ok);
  }

}
