package org.mybatis.generator.enums;

public enum Annotation {

    DATA("@Data", "lombok.Data"),
    Builder("@Builder", "lombok.Builder"),
    Accessors("@Accessors(chain = true)", "lombok.experimental.Accessors"),
    NoArgsConstructor("@NoArgsConstructor", "lombok.NoArgsConstructor"),
    AllArgsConstructor("@AllArgsConstructor", "lombok.AllArgsConstructor"),
    Mapper("@Mapper", "org.apache.ibatis.annotations.Mapper"),
    Param("@Param", "org.apache.ibatis.annotations.Param"),
    ApiModel("@ApiModel", "io.swagger.annotations.ApiModel"),
    ApiModelProperty("@ApiModelProperty", "io.swagger.annotations.ApiModelProperty");

    private String annotation;

    private String clazz;

    Annotation(String annotation, String clazz) {
        this.annotation = annotation;
        this.clazz = clazz;
    }

    public String getAnnotation() {
        return annotation;
    }

    public String getClazz() {
        return clazz;
    }

}
