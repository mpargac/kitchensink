package com.kitchensink.kitchensink.mapper;

import com.kitchensink.kitchensink.dto.MemberDTO;
import com.kitchensink.kitchensink.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "id", source = "extId")
    MemberDTO entityToDto(Member entity);

    @Mapping(target = "extId", source = "id")
    Member dtoToEntity(MemberDTO dto);
}
