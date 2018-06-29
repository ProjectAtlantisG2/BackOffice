package com.octest.dao;

import java.util.List;

import com.octest.beans.Device;

public interface DeviceDao {
    void add( Device device );
    void update( Device device );
    void delete( Device device );
    List<Device> lister();
    Device find( int id );
}