package com.jobapplication.restgeoreceiver.mapper;

import com.jobapplication.restgeoreceiver.domain.Device;
import com.jobapplication.restgeoreceiver.domain.dto.DeviceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceMapper {
    
    public Device mapToDevice(DeviceDto deviceDto) {
        return new Device(deviceDto.getDeviceId(), deviceDto.getDeviceName());
    }
    
    public DeviceDto mapToDeviceDto(Device device) {
        return new DeviceDto(device.getDeviceId(), device.getDeviceName());
    }

    public List<Device> mapToDeviceList(final List<DeviceDto> geolocationDtoList) {
        return geolocationDtoList.stream().map(this::mapToDevice).collect(Collectors.toList());
    }

    public List<DeviceDto> mapToDeviceDtoList(final List<Device> geolocationList) {
        return geolocationList.stream().map(this::mapToDeviceDto).collect(Collectors.toList());
    }

}
