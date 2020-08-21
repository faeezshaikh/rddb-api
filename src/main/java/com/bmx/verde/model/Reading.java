package com.bmx.verde.model;

import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "READING")
public class Reading {
    @EmbeddedId
    private ReadingPk id;

    private Long well1;
    private Long well2;
    private Long well3;
    private Long well4;
    private Long well5;
    private Long well6;
    private Long well7;
    private Long well8;
    private Long well9;
    private Long well10;
    private Long well11;
    private Long well12;
    private Long well13;
    private Long well14;
    private Long well15;
    private Long well16;
    private Long well17;
    private Long well18;
    private Long well19;
    private Long well20;
    private Long well21;
    private Long well22;
    private Long well23;
    private Long well24;
    private Long well25;
    private Long well26;
    private Long well27;
    private Long well28;
    private Long well29;
    private Long well30;
    private Long well31;
    private Long well32;
    private Long well33;
    private Long well34;
    private Long well35;
    private Long well36;
    private Long well37;
    private Long well38;
    private Long well39;
    private Long well40;
    private Long well41;
    private Long well42;
    private Long well43;
    private Long well44;
    private Long well45;
    private Long well46;
    private Long well47;
    private Long well48;
    private Long well49;
    private Long well50;
    private Long well51;
    private Long well52;
    private Long well53;
    private Long well54;
    private Long well55;
    private Long well56;
    private Long well57;
    private Long well58;
    private Long well59;
    private Long well60;
    private Long well61;
    private Long well62;
    private Long well63;
    private Long well64;
    private Long well65;
    private Long well66;
    private Long well67;
    private Long well68;
    private Long well69;
    private Long well70;
    private Long well71;
    private Long well72;
    private Long well73;
    private Long well74;
    private Long well75;
    private Long well76;
    private Long well77;
    private Long well78;
    private Long well79;
    private Long well80;
    private Long well81;
    private Long well82;
    private Long well83;
    private Long well84;
    private Long well85;
    private Long well86;
    private Long well87;
    private Long well88;
    private Long well89;
    private Long well90;
    private Long well91;
    private Long well92;
    private Long well93;
    private Long well94;
    private Long well95;
    private Long well96;
    private Long well97;
    private Long well98;
    private Long well99;
    private Long well100;
    private Long well101;
    private Long well102;
    private Long well103;
    private Long well104;

    public Reading() {
    }

    public Reading(ReadingPk id, Long well1, Long well2, Long well3, Long well4, Long well5, Long well6, Long well7,
            Long well8, Long well9, Long well10, Long well11, Long well12, Long well13, Long well14, Long well15,
            Long well16, Long well17, Long well18, Long well19, Long well20, Long well21, Long well22, Long well23,
            Long well24, Long well25, Long well26, Long well27, Long well28, Long well29, Long well30, Long well31,
            Long well32, Long well33, Long well34, Long well35, Long well36, Long well37, Long well38, Long well39,
            Long well40, Long well41, Long well42, Long well43, Long well44, Long well45, Long well46, Long well47,
            Long well48, Long well49, Long well50, Long well51, Long well52, Long well53, Long well54, Long well55,
            Long well56, Long well57, Long well58, Long well59, Long well60, Long well61, Long well62, Long well63,
            Long well64, Long well65, Long well66, Long well67, Long well68, Long well69, Long well70, Long well71,
            Long well72, Long well73, Long well74, Long well75, Long well76, Long well77, Long well78, Long well79,
            Long well80, Long well81, Long well82, Long well83, Long well84, Long well85, Long well86, Long well87,
            Long well88, Long well89, Long well90, Long well91, Long well92, Long well93, Long well94, Long well95,
            Long well96, Long well97, Long well98, Long well99, Long well100, Long well101, Long well102, Long well103,
            Long well104) {
        this.id = id;
        this.well1 = well1;
        this.well2 = well2;
        this.well3 = well3;
        this.well4 = well4;
        this.well5 = well5;
        this.well6 = well6;
        this.well7 = well7;
        this.well8 = well8;
        this.well9 = well9;
        this.well10 = well10;
        this.well11 = well11;
        this.well12 = well12;
        this.well13 = well13;
        this.well14 = well14;
        this.well15 = well15;
        this.well16 = well16;
        this.well17 = well17;
        this.well18 = well18;
        this.well19 = well19;
        this.well20 = well20;
        this.well21 = well21;
        this.well22 = well22;
        this.well23 = well23;
        this.well24 = well24;
        this.well25 = well25;
        this.well26 = well26;
        this.well27 = well27;
        this.well28 = well28;
        this.well29 = well29;
        this.well30 = well30;
        this.well31 = well31;
        this.well32 = well32;
        this.well33 = well33;
        this.well34 = well34;
        this.well35 = well35;
        this.well36 = well36;
        this.well37 = well37;
        this.well38 = well38;
        this.well39 = well39;
        this.well40 = well40;
        this.well41 = well41;
        this.well42 = well42;
        this.well43 = well43;
        this.well44 = well44;
        this.well45 = well45;
        this.well46 = well46;
        this.well47 = well47;
        this.well48 = well48;
        this.well49 = well49;
        this.well50 = well50;
        this.well51 = well51;
        this.well52 = well52;
        this.well53 = well53;
        this.well54 = well54;
        this.well55 = well55;
        this.well56 = well56;
        this.well57 = well57;
        this.well58 = well58;
        this.well59 = well59;
        this.well60 = well60;
        this.well61 = well61;
        this.well62 = well62;
        this.well63 = well63;
        this.well64 = well64;
        this.well65 = well65;
        this.well66 = well66;
        this.well67 = well67;
        this.well68 = well68;
        this.well69 = well69;
        this.well70 = well70;
        this.well71 = well71;
        this.well72 = well72;
        this.well73 = well73;
        this.well74 = well74;
        this.well75 = well75;
        this.well76 = well76;
        this.well77 = well77;
        this.well78 = well78;
        this.well79 = well79;
        this.well80 = well80;
        this.well81 = well81;
        this.well82 = well82;
        this.well83 = well83;
        this.well84 = well84;
        this.well85 = well85;
        this.well86 = well86;
        this.well87 = well87;
        this.well88 = well88;
        this.well89 = well89;
        this.well90 = well90;
        this.well91 = well91;
        this.well92 = well92;
        this.well93 = well93;
        this.well94 = well94;
        this.well95 = well95;
        this.well96 = well96;
        this.well97 = well97;
        this.well98 = well98;
        this.well99 = well99;
        this.well100 = well100;
        this.well101 = well101;
        this.well102 = well102;
        this.well103 = well103;
        this.well104 = well104;
    }

    public ReadingPk getId() {
        return this.id;
    }

    public void setId(ReadingPk id) {
        this.id = id;
    }

    public Long getWell1() {
        return this.well1;
    }

    public void setWell1(Long well1) {
        this.well1 = well1;
    }

    public Long getWell2() {
        return this.well2;
    }

    public void setWell2(Long well2) {
        this.well2 = well2;
    }

    public Long getWell3() {
        return this.well3;
    }

    public void setWell3(Long well3) {
        this.well3 = well3;
    }

    public Long getWell4() {
        return this.well4;
    }

    public void setWell4(Long well4) {
        this.well4 = well4;
    }

    public Long getWell5() {
        return this.well5;
    }

    public void setWell5(Long well5) {
        this.well5 = well5;
    }

    public Long getWell6() {
        return this.well6;
    }

    public void setWell6(Long well6) {
        this.well6 = well6;
    }

    public Long getWell7() {
        return this.well7;
    }

    public void setWell7(Long well7) {
        this.well7 = well7;
    }

    public Long getWell8() {
        return this.well8;
    }

    public void setWell8(Long well8) {
        this.well8 = well8;
    }

    public Long getWell9() {
        return this.well9;
    }

    public void setWell9(Long well9) {
        this.well9 = well9;
    }

    public Long getWell10() {
        return this.well10;
    }

    public void setWell10(Long well10) {
        this.well10 = well10;
    }

    public Long getWell11() {
        return this.well11;
    }

    public void setWell11(Long well11) {
        this.well11 = well11;
    }

    public Long getWell12() {
        return this.well12;
    }

    public void setWell12(Long well12) {
        this.well12 = well12;
    }

    public Long getWell13() {
        return this.well13;
    }

    public void setWell13(Long well13) {
        this.well13 = well13;
    }

    public Long getWell14() {
        return this.well14;
    }

    public void setWell14(Long well14) {
        this.well14 = well14;
    }

    public Long getWell15() {
        return this.well15;
    }

    public void setWell15(Long well15) {
        this.well15 = well15;
    }

    public Long getWell16() {
        return this.well16;
    }

    public void setWell16(Long well16) {
        this.well16 = well16;
    }

    public Long getWell17() {
        return this.well17;
    }

    public void setWell17(Long well17) {
        this.well17 = well17;
    }

    public Long getWell18() {
        return this.well18;
    }

    public void setWell18(Long well18) {
        this.well18 = well18;
    }

    public Long getWell19() {
        return this.well19;
    }

    public void setWell19(Long well19) {
        this.well19 = well19;
    }

    public Long getWell20() {
        return this.well20;
    }

    public void setWell20(Long well20) {
        this.well20 = well20;
    }

    public Long getWell21() {
        return this.well21;
    }

    public void setWell21(Long well21) {
        this.well21 = well21;
    }

    public Long getWell22() {
        return this.well22;
    }

    public void setWell22(Long well22) {
        this.well22 = well22;
    }

    public Long getWell23() {
        return this.well23;
    }

    public void setWell23(Long well23) {
        this.well23 = well23;
    }

    public Long getWell24() {
        return this.well24;
    }

    public void setWell24(Long well24) {
        this.well24 = well24;
    }

    public Long getWell25() {
        return this.well25;
    }

    public void setWell25(Long well25) {
        this.well25 = well25;
    }

    public Long getWell26() {
        return this.well26;
    }

    public void setWell26(Long well26) {
        this.well26 = well26;
    }

    public Long getWell27() {
        return this.well27;
    }

    public void setWell27(Long well27) {
        this.well27 = well27;
    }

    public Long getWell28() {
        return this.well28;
    }

    public void setWell28(Long well28) {
        this.well28 = well28;
    }

    public Long getWell29() {
        return this.well29;
    }

    public void setWell29(Long well29) {
        this.well29 = well29;
    }

    public Long getWell30() {
        return this.well30;
    }

    public void setWell30(Long well30) {
        this.well30 = well30;
    }

    public Long getWell31() {
        return this.well31;
    }

    public void setWell31(Long well31) {
        this.well31 = well31;
    }

    public Long getWell32() {
        return this.well32;
    }

    public void setWell32(Long well32) {
        this.well32 = well32;
    }

    public Long getWell33() {
        return this.well33;
    }

    public void setWell33(Long well33) {
        this.well33 = well33;
    }

    public Long getWell34() {
        return this.well34;
    }

    public void setWell34(Long well34) {
        this.well34 = well34;
    }

    public Long getWell35() {
        return this.well35;
    }

    public void setWell35(Long well35) {
        this.well35 = well35;
    }

    public Long getWell36() {
        return this.well36;
    }

    public void setWell36(Long well36) {
        this.well36 = well36;
    }

    public Long getWell37() {
        return this.well37;
    }

    public void setWell37(Long well37) {
        this.well37 = well37;
    }

    public Long getWell38() {
        return this.well38;
    }

    public void setWell38(Long well38) {
        this.well38 = well38;
    }

    public Long getWell39() {
        return this.well39;
    }

    public void setWell39(Long well39) {
        this.well39 = well39;
    }

    public Long getWell40() {
        return this.well40;
    }

    public void setWell40(Long well40) {
        this.well40 = well40;
    }

    public Long getWell41() {
        return this.well41;
    }

    public void setWell41(Long well41) {
        this.well41 = well41;
    }

    public Long getWell42() {
        return this.well42;
    }

    public void setWell42(Long well42) {
        this.well42 = well42;
    }

    public Long getWell43() {
        return this.well43;
    }

    public void setWell43(Long well43) {
        this.well43 = well43;
    }

    public Long getWell44() {
        return this.well44;
    }

    public void setWell44(Long well44) {
        this.well44 = well44;
    }

    public Long getWell45() {
        return this.well45;
    }

    public void setWell45(Long well45) {
        this.well45 = well45;
    }

    public Long getWell46() {
        return this.well46;
    }

    public void setWell46(Long well46) {
        this.well46 = well46;
    }

    public Long getWell47() {
        return this.well47;
    }

    public void setWell47(Long well47) {
        this.well47 = well47;
    }

    public Long getWell48() {
        return this.well48;
    }

    public void setWell48(Long well48) {
        this.well48 = well48;
    }

    public Long getWell49() {
        return this.well49;
    }

    public void setWell49(Long well49) {
        this.well49 = well49;
    }

    public Long getWell50() {
        return this.well50;
    }

    public void setWell50(Long well50) {
        this.well50 = well50;
    }

    public Long getWell51() {
        return this.well51;
    }

    public void setWell51(Long well51) {
        this.well51 = well51;
    }

    public Long getWell52() {
        return this.well52;
    }

    public void setWell52(Long well52) {
        this.well52 = well52;
    }

    public Long getWell53() {
        return this.well53;
    }

    public void setWell53(Long well53) {
        this.well53 = well53;
    }

    public Long getWell54() {
        return this.well54;
    }

    public void setWell54(Long well54) {
        this.well54 = well54;
    }

    public Long getWell55() {
        return this.well55;
    }

    public void setWell55(Long well55) {
        this.well55 = well55;
    }

    public Long getWell56() {
        return this.well56;
    }

    public void setWell56(Long well56) {
        this.well56 = well56;
    }

    public Long getWell57() {
        return this.well57;
    }

    public void setWell57(Long well57) {
        this.well57 = well57;
    }

    public Long getWell58() {
        return this.well58;
    }

    public void setWell58(Long well58) {
        this.well58 = well58;
    }

    public Long getWell59() {
        return this.well59;
    }

    public void setWell59(Long well59) {
        this.well59 = well59;
    }

    public Long getWell60() {
        return this.well60;
    }

    public void setWell60(Long well60) {
        this.well60 = well60;
    }

    public Long getWell61() {
        return this.well61;
    }

    public void setWell61(Long well61) {
        this.well61 = well61;
    }

    public Long getWell62() {
        return this.well62;
    }

    public void setWell62(Long well62) {
        this.well62 = well62;
    }

    public Long getWell63() {
        return this.well63;
    }

    public void setWell63(Long well63) {
        this.well63 = well63;
    }

    public Long getWell64() {
        return this.well64;
    }

    public void setWell64(Long well64) {
        this.well64 = well64;
    }

    public Long getWell65() {
        return this.well65;
    }

    public void setWell65(Long well65) {
        this.well65 = well65;
    }

    public Long getWell66() {
        return this.well66;
    }

    public void setWell66(Long well66) {
        this.well66 = well66;
    }

    public Long getWell67() {
        return this.well67;
    }

    public void setWell67(Long well67) {
        this.well67 = well67;
    }

    public Long getWell68() {
        return this.well68;
    }

    public void setWell68(Long well68) {
        this.well68 = well68;
    }

    public Long getWell69() {
        return this.well69;
    }

    public void setWell69(Long well69) {
        this.well69 = well69;
    }

    public Long getWell70() {
        return this.well70;
    }

    public void setWell70(Long well70) {
        this.well70 = well70;
    }

    public Long getWell71() {
        return this.well71;
    }

    public void setWell71(Long well71) {
        this.well71 = well71;
    }

    public Long getWell72() {
        return this.well72;
    }

    public void setWell72(Long well72) {
        this.well72 = well72;
    }

    public Long getWell73() {
        return this.well73;
    }

    public void setWell73(Long well73) {
        this.well73 = well73;
    }

    public Long getWell74() {
        return this.well74;
    }

    public void setWell74(Long well74) {
        this.well74 = well74;
    }

    public Long getWell75() {
        return this.well75;
    }

    public void setWell75(Long well75) {
        this.well75 = well75;
    }

    public Long getWell76() {
        return this.well76;
    }

    public void setWell76(Long well76) {
        this.well76 = well76;
    }

    public Long getWell77() {
        return this.well77;
    }

    public void setWell77(Long well77) {
        this.well77 = well77;
    }

    public Long getWell78() {
        return this.well78;
    }

    public void setWell78(Long well78) {
        this.well78 = well78;
    }

    public Long getWell79() {
        return this.well79;
    }

    public void setWell79(Long well79) {
        this.well79 = well79;
    }

    public Long getWell80() {
        return this.well80;
    }

    public void setWell80(Long well80) {
        this.well80 = well80;
    }

    public Long getWell81() {
        return this.well81;
    }

    public void setWell81(Long well81) {
        this.well81 = well81;
    }

    public Long getWell82() {
        return this.well82;
    }

    public void setWell82(Long well82) {
        this.well82 = well82;
    }

    public Long getWell83() {
        return this.well83;
    }

    public void setWell83(Long well83) {
        this.well83 = well83;
    }

    public Long getWell84() {
        return this.well84;
    }

    public void setWell84(Long well84) {
        this.well84 = well84;
    }

    public Long getWell85() {
        return this.well85;
    }

    public void setWell85(Long well85) {
        this.well85 = well85;
    }

    public Long getWell86() {
        return this.well86;
    }

    public void setWell86(Long well86) {
        this.well86 = well86;
    }

    public Long getWell87() {
        return this.well87;
    }

    public void setWell87(Long well87) {
        this.well87 = well87;
    }

    public Long getWell88() {
        return this.well88;
    }

    public void setWell88(Long well88) {
        this.well88 = well88;
    }

    public Long getWell89() {
        return this.well89;
    }

    public void setWell89(Long well89) {
        this.well89 = well89;
    }

    public Long getWell90() {
        return this.well90;
    }

    public void setWell90(Long well90) {
        this.well90 = well90;
    }

    public Long getWell91() {
        return this.well91;
    }

    public void setWell91(Long well91) {
        this.well91 = well91;
    }

    public Long getWell92() {
        return this.well92;
    }

    public void setWell92(Long well92) {
        this.well92 = well92;
    }

    public Long getWell93() {
        return this.well93;
    }

    public void setWell93(Long well93) {
        this.well93 = well93;
    }

    public Long getWell94() {
        return this.well94;
    }

    public void setWell94(Long well94) {
        this.well94 = well94;
    }

    public Long getWell95() {
        return this.well95;
    }

    public void setWell95(Long well95) {
        this.well95 = well95;
    }

    public Long getWell96() {
        return this.well96;
    }

    public void setWell96(Long well96) {
        this.well96 = well96;
    }

    public Long getWell97() {
        return this.well97;
    }

    public void setWell97(Long well97) {
        this.well97 = well97;
    }

    public Long getWell98() {
        return this.well98;
    }

    public void setWell98(Long well98) {
        this.well98 = well98;
    }

    public Long getWell99() {
        return this.well99;
    }

    public void setWell99(Long well99) {
        this.well99 = well99;
    }

    public Long getWell100() {
        return this.well100;
    }

    public void setWell100(Long well100) {
        this.well100 = well100;
    }

    public Long getWell101() {
        return this.well101;
    }

    public void setWell101(Long well101) {
        this.well101 = well101;
    }

    public Long getWell102() {
        return this.well102;
    }

    public void setWell102(Long well102) {
        this.well102 = well102;
    }

    public Long getWell103() {
        return this.well103;
    }

    public void setWell103(Long well103) {
        this.well103 = well103;
    }

    public Long getWell104() {
        return this.well104;
    }

    public void setWell104(Long well104) {
        this.well104 = well104;
    }

    public Reading id(ReadingPk id) {
        this.id = id;
        return this;
    }

    public Reading well1(Long well1) {
        this.well1 = well1;
        return this;
    }

    public Reading well2(Long well2) {
        this.well2 = well2;
        return this;
    }

    public Reading well3(Long well3) {
        this.well3 = well3;
        return this;
    }

    public Reading well4(Long well4) {
        this.well4 = well4;
        return this;
    }

    public Reading well5(Long well5) {
        this.well5 = well5;
        return this;
    }

    public Reading well6(Long well6) {
        this.well6 = well6;
        return this;
    }

    public Reading well7(Long well7) {
        this.well7 = well7;
        return this;
    }

    public Reading well8(Long well8) {
        this.well8 = well8;
        return this;
    }

    public Reading well9(Long well9) {
        this.well9 = well9;
        return this;
    }

    public Reading well10(Long well10) {
        this.well10 = well10;
        return this;
    }

    public Reading well11(Long well11) {
        this.well11 = well11;
        return this;
    }

    public Reading well12(Long well12) {
        this.well12 = well12;
        return this;
    }

    public Reading well13(Long well13) {
        this.well13 = well13;
        return this;
    }

    public Reading well14(Long well14) {
        this.well14 = well14;
        return this;
    }

    public Reading well15(Long well15) {
        this.well15 = well15;
        return this;
    }

    public Reading well16(Long well16) {
        this.well16 = well16;
        return this;
    }

    public Reading well17(Long well17) {
        this.well17 = well17;
        return this;
    }

    public Reading well18(Long well18) {
        this.well18 = well18;
        return this;
    }

    public Reading well19(Long well19) {
        this.well19 = well19;
        return this;
    }

    public Reading well20(Long well20) {
        this.well20 = well20;
        return this;
    }

    public Reading well21(Long well21) {
        this.well21 = well21;
        return this;
    }

    public Reading well22(Long well22) {
        this.well22 = well22;
        return this;
    }

    public Reading well23(Long well23) {
        this.well23 = well23;
        return this;
    }

    public Reading well24(Long well24) {
        this.well24 = well24;
        return this;
    }

    public Reading well25(Long well25) {
        this.well25 = well25;
        return this;
    }

    public Reading well26(Long well26) {
        this.well26 = well26;
        return this;
    }

    public Reading well27(Long well27) {
        this.well27 = well27;
        return this;
    }

    public Reading well28(Long well28) {
        this.well28 = well28;
        return this;
    }

    public Reading well29(Long well29) {
        this.well29 = well29;
        return this;
    }

    public Reading well30(Long well30) {
        this.well30 = well30;
        return this;
    }

    public Reading well31(Long well31) {
        this.well31 = well31;
        return this;
    }

    public Reading well32(Long well32) {
        this.well32 = well32;
        return this;
    }

    public Reading well33(Long well33) {
        this.well33 = well33;
        return this;
    }

    public Reading well34(Long well34) {
        this.well34 = well34;
        return this;
    }

    public Reading well35(Long well35) {
        this.well35 = well35;
        return this;
    }

    public Reading well36(Long well36) {
        this.well36 = well36;
        return this;
    }

    public Reading well37(Long well37) {
        this.well37 = well37;
        return this;
    }

    public Reading well38(Long well38) {
        this.well38 = well38;
        return this;
    }

    public Reading well39(Long well39) {
        this.well39 = well39;
        return this;
    }

    public Reading well40(Long well40) {
        this.well40 = well40;
        return this;
    }

    public Reading well41(Long well41) {
        this.well41 = well41;
        return this;
    }

    public Reading well42(Long well42) {
        this.well42 = well42;
        return this;
    }

    public Reading well43(Long well43) {
        this.well43 = well43;
        return this;
    }

    public Reading well44(Long well44) {
        this.well44 = well44;
        return this;
    }

    public Reading well45(Long well45) {
        this.well45 = well45;
        return this;
    }

    public Reading well46(Long well46) {
        this.well46 = well46;
        return this;
    }

    public Reading well47(Long well47) {
        this.well47 = well47;
        return this;
    }

    public Reading well48(Long well48) {
        this.well48 = well48;
        return this;
    }

    public Reading well49(Long well49) {
        this.well49 = well49;
        return this;
    }

    public Reading well50(Long well50) {
        this.well50 = well50;
        return this;
    }

    public Reading well51(Long well51) {
        this.well51 = well51;
        return this;
    }

    public Reading well52(Long well52) {
        this.well52 = well52;
        return this;
    }

    public Reading well53(Long well53) {
        this.well53 = well53;
        return this;
    }

    public Reading well54(Long well54) {
        this.well54 = well54;
        return this;
    }

    public Reading well55(Long well55) {
        this.well55 = well55;
        return this;
    }

    public Reading well56(Long well56) {
        this.well56 = well56;
        return this;
    }

    public Reading well57(Long well57) {
        this.well57 = well57;
        return this;
    }

    public Reading well58(Long well58) {
        this.well58 = well58;
        return this;
    }

    public Reading well59(Long well59) {
        this.well59 = well59;
        return this;
    }

    public Reading well60(Long well60) {
        this.well60 = well60;
        return this;
    }

    public Reading well61(Long well61) {
        this.well61 = well61;
        return this;
    }

    public Reading well62(Long well62) {
        this.well62 = well62;
        return this;
    }

    public Reading well63(Long well63) {
        this.well63 = well63;
        return this;
    }

    public Reading well64(Long well64) {
        this.well64 = well64;
        return this;
    }

    public Reading well65(Long well65) {
        this.well65 = well65;
        return this;
    }

    public Reading well66(Long well66) {
        this.well66 = well66;
        return this;
    }

    public Reading well67(Long well67) {
        this.well67 = well67;
        return this;
    }

    public Reading well68(Long well68) {
        this.well68 = well68;
        return this;
    }

    public Reading well69(Long well69) {
        this.well69 = well69;
        return this;
    }

    public Reading well70(Long well70) {
        this.well70 = well70;
        return this;
    }

    public Reading well71(Long well71) {
        this.well71 = well71;
        return this;
    }

    public Reading well72(Long well72) {
        this.well72 = well72;
        return this;
    }

    public Reading well73(Long well73) {
        this.well73 = well73;
        return this;
    }

    public Reading well74(Long well74) {
        this.well74 = well74;
        return this;
    }

    public Reading well75(Long well75) {
        this.well75 = well75;
        return this;
    }

    public Reading well76(Long well76) {
        this.well76 = well76;
        return this;
    }

    public Reading well77(Long well77) {
        this.well77 = well77;
        return this;
    }

    public Reading well78(Long well78) {
        this.well78 = well78;
        return this;
    }

    public Reading well79(Long well79) {
        this.well79 = well79;
        return this;
    }

    public Reading well80(Long well80) {
        this.well80 = well80;
        return this;
    }

    public Reading well81(Long well81) {
        this.well81 = well81;
        return this;
    }

    public Reading well82(Long well82) {
        this.well82 = well82;
        return this;
    }

    public Reading well83(Long well83) {
        this.well83 = well83;
        return this;
    }

    public Reading well84(Long well84) {
        this.well84 = well84;
        return this;
    }

    public Reading well85(Long well85) {
        this.well85 = well85;
        return this;
    }

    public Reading well86(Long well86) {
        this.well86 = well86;
        return this;
    }

    public Reading well87(Long well87) {
        this.well87 = well87;
        return this;
    }

    public Reading well88(Long well88) {
        this.well88 = well88;
        return this;
    }

    public Reading well89(Long well89) {
        this.well89 = well89;
        return this;
    }

    public Reading well90(Long well90) {
        this.well90 = well90;
        return this;
    }

    public Reading well91(Long well91) {
        this.well91 = well91;
        return this;
    }

    public Reading well92(Long well92) {
        this.well92 = well92;
        return this;
    }

    public Reading well93(Long well93) {
        this.well93 = well93;
        return this;
    }

    public Reading well94(Long well94) {
        this.well94 = well94;
        return this;
    }

    public Reading well95(Long well95) {
        this.well95 = well95;
        return this;
    }

    public Reading well96(Long well96) {
        this.well96 = well96;
        return this;
    }

    public Reading well97(Long well97) {
        this.well97 = well97;
        return this;
    }

    public Reading well98(Long well98) {
        this.well98 = well98;
        return this;
    }

    public Reading well99(Long well99) {
        this.well99 = well99;
        return this;
    }

    public Reading well100(Long well100) {
        this.well100 = well100;
        return this;
    }

    public Reading well101(Long well101) {
        this.well101 = well101;
        return this;
    }

    public Reading well102(Long well102) {
        this.well102 = well102;
        return this;
    }

    public Reading well103(Long well103) {
        this.well103 = well103;
        return this;
    }

    public Reading well104(Long well104) {
        this.well104 = well104;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reading)) {
            return false;
        }
        Reading reading = (Reading) o;
        return Objects.equals(id, reading.id) && Objects.equals(well1, reading.well1)
                && Objects.equals(well2, reading.well2) && Objects.equals(well3, reading.well3)
                && Objects.equals(well4, reading.well4) && Objects.equals(well5, reading.well5)
                && Objects.equals(well6, reading.well6) && Objects.equals(well7, reading.well7)
                && Objects.equals(well8, reading.well8) && Objects.equals(well9, reading.well9)
                && Objects.equals(well10, reading.well10) && Objects.equals(well11, reading.well11)
                && Objects.equals(well12, reading.well12) && Objects.equals(well13, reading.well13)
                && Objects.equals(well14, reading.well14) && Objects.equals(well15, reading.well15)
                && Objects.equals(well16, reading.well16) && Objects.equals(well17, reading.well17)
                && Objects.equals(well18, reading.well18) && Objects.equals(well19, reading.well19)
                && Objects.equals(well20, reading.well20) && Objects.equals(well21, reading.well21)
                && Objects.equals(well22, reading.well22) && Objects.equals(well23, reading.well23)
                && Objects.equals(well24, reading.well24) && Objects.equals(well25, reading.well25)
                && Objects.equals(well26, reading.well26) && Objects.equals(well27, reading.well27)
                && Objects.equals(well28, reading.well28) && Objects.equals(well29, reading.well29)
                && Objects.equals(well30, reading.well30) && Objects.equals(well31, reading.well31)
                && Objects.equals(well32, reading.well32) && Objects.equals(well33, reading.well33)
                && Objects.equals(well34, reading.well34) && Objects.equals(well35, reading.well35)
                && Objects.equals(well36, reading.well36) && Objects.equals(well37, reading.well37)
                && Objects.equals(well38, reading.well38) && Objects.equals(well39, reading.well39)
                && Objects.equals(well40, reading.well40) && Objects.equals(well41, reading.well41)
                && Objects.equals(well42, reading.well42) && Objects.equals(well43, reading.well43)
                && Objects.equals(well44, reading.well44) && Objects.equals(well45, reading.well45)
                && Objects.equals(well46, reading.well46) && Objects.equals(well47, reading.well47)
                && Objects.equals(well48, reading.well48) && Objects.equals(well49, reading.well49)
                && Objects.equals(well50, reading.well50) && Objects.equals(well51, reading.well51)
                && Objects.equals(well52, reading.well52) && Objects.equals(well53, reading.well53)
                && Objects.equals(well54, reading.well54) && Objects.equals(well55, reading.well55)
                && Objects.equals(well56, reading.well56) && Objects.equals(well57, reading.well57)
                && Objects.equals(well58, reading.well58) && Objects.equals(well59, reading.well59)
                && Objects.equals(well60, reading.well60) && Objects.equals(well61, reading.well61)
                && Objects.equals(well62, reading.well62) && Objects.equals(well63, reading.well63)
                && Objects.equals(well64, reading.well64) && Objects.equals(well65, reading.well65)
                && Objects.equals(well66, reading.well66) && Objects.equals(well67, reading.well67)
                && Objects.equals(well68, reading.well68) && Objects.equals(well69, reading.well69)
                && Objects.equals(well70, reading.well70) && Objects.equals(well71, reading.well71)
                && Objects.equals(well72, reading.well72) && Objects.equals(well73, reading.well73)
                && Objects.equals(well74, reading.well74) && Objects.equals(well75, reading.well75)
                && Objects.equals(well76, reading.well76) && Objects.equals(well77, reading.well77)
                && Objects.equals(well78, reading.well78) && Objects.equals(well79, reading.well79)
                && Objects.equals(well80, reading.well80) && Objects.equals(well81, reading.well81)
                && Objects.equals(well82, reading.well82) && Objects.equals(well83, reading.well83)
                && Objects.equals(well84, reading.well84) && Objects.equals(well85, reading.well85)
                && Objects.equals(well86, reading.well86) && Objects.equals(well87, reading.well87)
                && Objects.equals(well88, reading.well88) && Objects.equals(well89, reading.well89)
                && Objects.equals(well90, reading.well90) && Objects.equals(well91, reading.well91)
                && Objects.equals(well92, reading.well92) && Objects.equals(well93, reading.well93)
                && Objects.equals(well94, reading.well94) && Objects.equals(well95, reading.well95)
                && Objects.equals(well96, reading.well96) && Objects.equals(well97, reading.well97)
                && Objects.equals(well98, reading.well98) && Objects.equals(well99, reading.well99)
                && Objects.equals(well100, reading.well100) && Objects.equals(well101, reading.well101)
                && Objects.equals(well102, reading.well102) && Objects.equals(well103, reading.well103)
                && Objects.equals(well104, reading.well104);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, well1, well2, well3, well4, well5, well6, well7, well8, well9, well10, well11, well12,
                well13, well14, well15, well16, well17, well18, well19, well20, well21, well22, well23, well24, well25,
                well26, well27, well28, well29, well30, well31, well32, well33, well34, well35, well36, well37, well38,
                well39, well40, well41, well42, well43, well44, well45, well46, well47, well48, well49, well50, well51,
                well52, well53, well54, well55, well56, well57, well58, well59, well60, well61, well62, well63, well64,
                well65, well66, well67, well68, well69, well70, well71, well72, well73, well74, well75, well76, well77,
                well78, well79, well80, well81, well82, well83, well84, well85, well86, well87, well88, well89, well90,
                well91, well92, well93, well94, well95, well96, well97, well98, well99, well100, well101, well102,
                well103, well104);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", well1='" + getWell1() + "'" + ", well2='" + getWell2() + "'"
                + ", well3='" + getWell3() + "'" + ", well4='" + getWell4() + "'" + ", well5='" + getWell5() + "'"
                + ", well6='" + getWell6() + "'" + ", well7='" + getWell7() + "'" + ", well8='" + getWell8() + "'"
                + ", well9='" + getWell9() + "'" + ", well10='" + getWell10() + "'" + ", well11='" + getWell11() + "'"
                + ", well12='" + getWell12() + "'" + ", well13='" + getWell13() + "'" + ", well14='" + getWell14() + "'"
                + ", well15='" + getWell15() + "'" + ", well16='" + getWell16() + "'" + ", well17='" + getWell17() + "'"
                + ", well18='" + getWell18() + "'" + ", well19='" + getWell19() + "'" + ", well20='" + getWell20() + "'"
                + ", well21='" + getWell21() + "'" + ", well22='" + getWell22() + "'" + ", well23='" + getWell23() + "'"
                + ", well24='" + getWell24() + "'" + ", well25='" + getWell25() + "'" + ", well26='" + getWell26() + "'"
                + ", well27='" + getWell27() + "'" + ", well28='" + getWell28() + "'" + ", well29='" + getWell29() + "'"
                + ", well30='" + getWell30() + "'" + ", well31='" + getWell31() + "'" + ", well32='" + getWell32() + "'"
                + ", well33='" + getWell33() + "'" + ", well34='" + getWell34() + "'" + ", well35='" + getWell35() + "'"
                + ", well36='" + getWell36() + "'" + ", well37='" + getWell37() + "'" + ", well38='" + getWell38() + "'"
                + ", well39='" + getWell39() + "'" + ", well40='" + getWell40() + "'" + ", well41='" + getWell41() + "'"
                + ", well42='" + getWell42() + "'" + ", well43='" + getWell43() + "'" + ", well44='" + getWell44() + "'"
                + ", well45='" + getWell45() + "'" + ", well46='" + getWell46() + "'" + ", well47='" + getWell47() + "'"
                + ", well48='" + getWell48() + "'" + ", well49='" + getWell49() + "'" + ", well50='" + getWell50() + "'"
                + ", well51='" + getWell51() + "'" + ", well52='" + getWell52() + "'" + ", well53='" + getWell53() + "'"
                + ", well54='" + getWell54() + "'" + ", well55='" + getWell55() + "'" + ", well56='" + getWell56() + "'"
                + ", well57='" + getWell57() + "'" + ", well58='" + getWell58() + "'" + ", well59='" + getWell59() + "'"
                + ", well60='" + getWell60() + "'" + ", well61='" + getWell61() + "'" + ", well62='" + getWell62() + "'"
                + ", well63='" + getWell63() + "'" + ", well64='" + getWell64() + "'" + ", well65='" + getWell65() + "'"
                + ", well66='" + getWell66() + "'" + ", well67='" + getWell67() + "'" + ", well68='" + getWell68() + "'"
                + ", well69='" + getWell69() + "'" + ", well70='" + getWell70() + "'" + ", well71='" + getWell71() + "'"
                + ", well72='" + getWell72() + "'" + ", well73='" + getWell73() + "'" + ", well74='" + getWell74() + "'"
                + ", well75='" + getWell75() + "'" + ", well76='" + getWell76() + "'" + ", well77='" + getWell77() + "'"
                + ", well78='" + getWell78() + "'" + ", well79='" + getWell79() + "'" + ", well80='" + getWell80() + "'"
                + ", well81='" + getWell81() + "'" + ", well82='" + getWell82() + "'" + ", well83='" + getWell83() + "'"
                + ", well84='" + getWell84() + "'" + ", well85='" + getWell85() + "'" + ", well86='" + getWell86() + "'"
                + ", well87='" + getWell87() + "'" + ", well88='" + getWell88() + "'" + ", well89='" + getWell89() + "'"
                + ", well90='" + getWell90() + "'" + ", well91='" + getWell91() + "'" + ", well92='" + getWell92() + "'"
                + ", well93='" + getWell93() + "'" + ", well94='" + getWell94() + "'" + ", well95='" + getWell95() + "'"
                + ", well96='" + getWell96() + "'" + ", well97='" + getWell97() + "'" + ", well98='" + getWell98() + "'"
                + ", well99='" + getWell99() + "'" + ", well100='" + getWell100() + "'" + ", well101='" + getWell101()
                + "'" + ", well102='" + getWell102() + "'" + ", well103='" + getWell103() + "'" + ", well104='"
                + getWell104() + "'" + "}";
    }
}
