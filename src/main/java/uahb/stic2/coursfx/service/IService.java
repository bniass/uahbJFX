package uahb.stic2.coursfx.service;


import uahb.stic2.coursfx.model.Service;

import java.util.List;

public interface IService {
    public List<Service> findAll() throws Exception;
    public Service save(Service specialite) throws Exception;
}
