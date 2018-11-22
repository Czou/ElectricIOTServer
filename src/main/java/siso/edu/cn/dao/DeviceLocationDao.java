package siso.edu.cn.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import siso.edu.cn.entity.DeviceLocationEntity;

@Transactional
@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DeviceLocationDao extends IDaoImpl<DeviceLocationEntity> {
}
