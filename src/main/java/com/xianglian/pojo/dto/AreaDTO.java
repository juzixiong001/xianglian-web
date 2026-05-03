package com.xianglian.pojo.dto;

import lombok.Data;

@Data
public class AreaDTO {
    private String province;
    private String city;
    private String district;
    private String county;
    private String town;
    private String village;

    public String buildFullArea() {
        StringBuilder sb = new StringBuilder();
        if (province != null && !province.isEmpty()) {
            sb.append(province);
        }
        if (city != null && !city.isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(city);
        }
        if (district != null && !district.isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(district);
        }
        if (county != null && !county.isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(county);
        }
        if (town != null && !town.isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(town);
        }
        if (village != null && !village.isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(village);
        }
        return sb.toString();
    }
}