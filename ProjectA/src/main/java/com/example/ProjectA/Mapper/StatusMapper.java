package com.example.ProjectA.Mapper;


import com.example.ProjectA.dto.Status.StatusDto;
import com.example.ProjectA.entity.Status;

public class StatusMapper {

    public static StatusDto StatusMapperToStatusDto(Status status) {
        StatusDto statusDto = new StatusDto();
        statusDto.setId(status.getId());
        statusDto.setName(status.getName());
        return statusDto;
    }
}
