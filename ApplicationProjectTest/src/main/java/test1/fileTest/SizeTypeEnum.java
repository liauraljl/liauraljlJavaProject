package test1.fileTest;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by liaura_ljl on 2019/8/23.
 */
@AllArgsConstructor
public enum  SizeTypeEnum {
    B(0,"B"),
    KB(1,"KB"),
    MB(2,"MB"),
    GB(3,"GB"),
    TB(4,"TB"),
    PB(5,"PB");

    @Getter
    private int type;

    @Getter
    private String unit;

    public static String getUnit (int type) {
        for (SizeTypeEnum e : SizeTypeEnum.values()) {
            if (e.getType()==type) {
                return e.getUnit();
            }
        }
        return null;
    }


    public static SizeTypeEnum of(int type) {
        for (SizeTypeEnum e : values()) {
            if (e.type==type) {
                return e;
            }
        }
        return null;
    }
}
