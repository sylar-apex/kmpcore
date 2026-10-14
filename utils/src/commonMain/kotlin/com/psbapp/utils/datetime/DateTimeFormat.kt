package com.psbapp.utils.datetime

enum class DateTimeFormat(val value: String) {

    // ==================== BASIC DATE FORMATS ====================

    // Date formats with dash separator
    dd_MM_yyyy("dd-MM-yyyy"), // Eg. 18-01-2026
    MM_dd_yyyy("MM-dd-yyyy"), // Eg. 01-18-2026
    yyyy_MM_dd("yyyy-MM-dd"), // Eg. 2026-01-18
    yyyy_dd_MM("yyyy-dd-MM"), // Eg. 2026-18-01
    dd_MM_yy("dd-MM-yy"), // Eg. 18-01-26
    MM_dd_yy("MM-dd-yy"), // Eg. 01-18-26
    yy_MM_dd("yy-MM-dd"), // Eg. 26-01-18
    yy_dd_MM("yy-dd-MM"), // Eg. 26-18-01

    // Date formats with slash separator
    dd_MM_yyyy_slash("dd/MM/yyyy"), // Eg. 18/01/2026
    MM_dd_yyyy_slash("MM/dd/yyyy"), // Eg. 01/18/2026
    yyyy_MM_dd_slash("yyyy/MM/dd"), // Eg. 2026/01/18
    yyyy_dd_MM_slash("yyyy/dd/MM"), // Eg. 2026/18/01
    dd_MM_yy_slash("dd/MM/yy"), // Eg. 18/01/26
    MM_dd_yy_slash("MM/dd/yy"), // Eg. 01/18/26
    yy_MM_dd_slash("yy/MM/dd"), // Eg. 26/01/18

    // Date formats with dot separator
    dd_MM_yyyy_dot("dd.MM.yyyy"), // Eg. 18.01.2026
    MM_dd_yyyy_dot("MM.dd.yyyy"), // Eg. 01.18.2026
    yyyy_MM_dd_dot("yyyy.MM.dd"), // Eg. 2026.01.18
    dd_MM_yy_dot("dd.MM.yy"), // Eg. 18.01.26
    MM_dd_yy_dot("MM.dd.yy"), // Eg. 01.18.26
    yy_MM_dd_dot("yy.MM.dd"), // Eg. 26.01.18

    // Date formats with space separator
    dd_MM_yyyy_space("dd MM yyyy"), // Eg. 18 01 2026
    MM_dd_yyyy_space("MM dd yyyy"), // Eg. 01 18 2026
    yyyy_MM_dd_space("yyyy MM dd"), // Eg. 2026 01 18

    // Short month name formats
    dd_MMM_yyyy("dd MMM yyyy"), // Eg. 18 Jan 2026
    MMM_dd_yyyy("MMM dd, yyyy"), // Eg. Jan 18, 2026
    yyyy_MMM_dd("yyyy MMM dd"), // Eg. 2026 Jan 18
    dd_MMM_yy("dd MMM yy"), // Eg. 18 Jan 26
    MMM_dd_yy("MMM dd, yy"), // Eg. Jan 18, 26
    dd_MMM_yyyy_dash("dd-MMM-yyyy"), // Eg. 18-Jan-2026
    dd_MMM_yyyy_slash("dd/MMM/yyyy"), // Eg. 18/Jan/2026
    MMM_dd_yyyy_dash("MMM-dd-yyyy"), // Eg. Jan-18-2026
    yyyy_MMM_dd_dash("yyyy-MMM-dd"), // Eg. 2026-Jan-18

    // Full month name formats
    dd_MMMM_yyyy("dd MMMM yyyy"), // Eg. 18 January 2026
    MMMM_dd_yyyy("MMMM dd, yyyy"), // Eg. January 18, 2026
    yyyy_MMMM_dd("yyyy MMMM dd"), // Eg. 2026 January 18
    dd_MMMM_yy("dd MMMM yy"), // Eg. 18 January 26
    MMMM_dd_yy("MMMM dd, yy"), // Eg. January 18, 26
    dd_MMMM_yyyy_dash("dd-MMMM-yyyy"), // Eg. 18-January-2026
    MMMM_dd_yyyy_dash("MMMM-dd-yyyy"), // Eg. January-18-2026

    // Day of week formats
    EEEE_dd_MMMM_yyyy("EEEE, dd MMMM yyyy"), // Eg. Sunday, 18 January 2026
    EEE_dd_MMM_yyyy("EEE, dd MMM yyyy"), // Eg. Sun, 18 Jan 2026
    EEEE_MMMM_dd_yyyy("EEEE, MMMM dd, yyyy"), // Eg. Sunday, January 18, 2026
    EEE_MMM_dd_yyyy("EEE, MMM dd, yyyy"), // Eg. Sun, Jan 18, 2026
    EEEE_dd_MM_yyyy("EEEE, dd-MM-yyyy"), // Eg. Sunday, 18-01-2026
    EEE_dd_MM_yyyy("EEE, dd-MM-yyyy"), // Eg. Sun, 18-01-2026
    EEEE_dd_MM_yyyy_slash("EEEE, dd/MM/yyyy"), // Eg. Sunday, 18/01/2026
    EEE_dd_MM_yyyy_slash("EEE, dd/MM/yyyy"), // Eg. Sun, 18/01/2026
    E_dd_MMM_yyyy("E, dd MMM yyyy"), // Eg. Sun, 18 Jan 2026
    E_MMM_dd_yyyy("E MMM dd yyyy"), // Eg. Sun Jan 18 2026

    // Compact date formats
    ddMMyyyy("ddMMyyyy"), // Eg. 18012026
    MMddyyyy("MMddyyyy"), // Eg. 01182026
    yyyyMMdd("yyyyMMdd"), // Eg. 20260118
    ddMMyy("ddMMyy"), // Eg. 180126
    MMddyy("MMddyy"), // Eg. 011826
    yyMMdd("yyMMdd"), // Eg. 260118

    // Month and year only formats
    MM_yyyy("MM-yyyy"), // Eg. 01-2026
    yyyy_MM("yyyy-MM"), // Eg. 2026-01
    MM_yyyy_slash("MM/yyyy"), // Eg. 01/2026
    yyyy_MM_slash("yyyy/MM"), // Eg. 2026/01
    MMM_yyyy("MMM yyyy"), // Eg. Jan 2026
    MMMM_yyyy("MMMM yyyy"), // Eg. January 2026
    yyyy_MMM("yyyy MMM"), // Eg. 2026 Jan
    yyyy_MMMM("yyyy MMMM"), // Eg. 2026 January
    MMM_yy("MMM yy"), // Eg. Jan 26
    MMMM_yy("MMMM yy"), // Eg. January 26

    // Year only
    yyyy("yyyy"), // Eg. 2026
    yy("yy"), // Eg. 26

    // Partial formats
    dd_MMM("dd MMM"), // Eg. 18 Jan
    MMM_dd("MMM dd"), // Eg. Jan 18
    dd_MMMM("dd MMMM"), // Eg. 18 January
    MMMM_dd("MMMM dd"), // Eg. January 18
    E_dd_MMM("E dd MMM"), // Eg. Sun 18 Jan
    dd_MM("dd-MM"), // Eg. 18-01
    MM_dd("MM-dd"), // Eg. 01-18
    dd_MM_slash("dd/MM"), // Eg. 18/01
    MM_dd_slash("MM/dd"), // Eg. 01/18

    // Day of week only
    EEE("EEE"), // Eg. Sun
    EEEE("EEEE"), // Eg. Sunday
    E("E"), // Eg. Sun

    // Month only
    MMM("MMM"), // Eg. Jan
    MMMM("MMMM"), // Eg. January
    MM("MM"), // Eg. 01
    M("M"), // Eg. 1

    // Day only
    dd("dd"), // Eg. 18
    d("d"), // Eg. 18
    Do("do"), // Eg. 18th (ordinal)

    // ==================== ORDINAL DATE FORMATS (ISO 8601) ====================

    yyyy_DDD("yyyy-DDD"), // Eg. 2026-018 (day 18 of year)
    yyyyDDD("yyyyDDD"), // Eg. 2026018
    yy_DDD("yy-DDD"), // Eg. 26-018
    yyDDD("yyDDD"), // Eg. 26018

    // ==================== WEEK DATE FORMATS (ISO 8601) ====================

    YYYY_Www_D("YYYY-'W'ww-D"), // Eg. 2026-W03-7 (ISO week date)
    YYYYWwwD("YYYY'W'wwD"), // Eg. 2026W037
    YYYY_Www("YYYY-'W'ww"), // Eg. 2026-W03
    YYYYWww("YYYY'W'ww"), // Eg. 2026W03
    ww("ww"), // Eg. 03 (week number)
    w("w"), // Eg. 3 (week number)

    // ==================== TIME FORMATS ====================

    // Time formats (24-hour)
    HH_mm("HH:mm"), // Eg. 14:30
    HH_mm_ss("HH:mm:ss"), // Eg. 14:30:45
    HH_mm_ss_SSS("HH:mm:ss.SSS"), // Eg. 14:30:45.123
    HH_mm_ss_SSSSSS("HH:mm:ss.SSSSSS"), // Eg. 14:30:45.123456 (microseconds)
    HH_mm_ss_SSSSSSSSS("HH:mm:ss.SSSSSSSSS"), // Eg. 14:30:45.123456789 (nanoseconds)
    HHmm("HHmm"), // Eg. 1430
    HHmmss("HHmmss"), // Eg. 143045
    HHmmssSSS("HHmmssSSS"), // Eg. 143045123
    H_mm("H:mm"), // Eg. 14:30 (single digit hour)
    H_mm_ss("H:mm:ss"), // Eg. 14:30:45

    // Time formats (12-hour)
    hh_mm_a("hh:mm a"), // Eg. 02:30 PM
    hh_mm_ss_a("hh:mm:ss a"), // Eg. 02:30:45 PM
    hh_mm_ss_SSS_a("hh:mm:ss.SSS a"), // Eg. 02:30:45.123 PM
    h_mm_a("h:mm a"), // Eg. 2:30 PM
    h_mm_ss_a("h:mm:ss a"), // Eg. 2:30:45 PM
    hh_mm_A("hh:mm a"), // Eg. 02:30 PM
    h_mm_A("h:mm a"), // Eg. 2:30 PM

    // Time with timezone
    HH_mm_z("HH:mm z"), // Eg. 14:30 IST
    HH_mm_ss_z("HH:mm:ss z"), // Eg. 14:30:45 IST
    HH_mm_Z("HH:mm Z"), // Eg. 14:30 +0530
    HH_mm_ss_Z("HH:mm:ss Z"), // Eg. 14:30:45 +0530
    HH_mm_XXX("HH:mm XXX"), // Eg. 14:30 +05:30
    HH_mm_ss_XXX("HH:mm:ss XXX"), // Eg. 14:30:45 +05:30
    HH_mm_ss_SSS_XXX("HH:mm:ss.SSS XXX"), // Eg. 14:30:45.123 +05:30
    HH_mm_zzzz("HH:mm zzzz"), // Eg. 14:30 Indian Standard Time
    HH_mm_ss_zzzz("HH:mm:ss zzzz"), // Eg. 14:30:45 Indian Standard Time
    HH_mm_VV("HH:mm VV"), // Eg. 14:30 Asia/Kolkata
    HH_mm_ss_VV("HH:mm:ss VV"), // Eg. 14:30:45 Asia/Kolkata

    // Time only components
    mm_ss("mm:ss"), // Eg. 30:45
    ss("ss"), // Eg. 45
    SSS("SSS"), // Eg. 123 (milliseconds)
    SSSSSS("SSSSSS"), // Eg. 123456 (microseconds)

    // ==================== DATETIME FORMATS ====================

    // DateTime formats with dash separator (24-hour)
    dd_MM_yyyy_HH_mm("dd-MM-yyyy HH:mm"), // Eg. 18-01-2026 14:30
    dd_MM_yyyy_HH_mm_ss("dd-MM-yyyy HH:mm:ss"), // Eg. 18-01-2026 14:30:45
    dd_MM_yyyy_HH_mm_ss_SSS("dd-MM-yyyy HH:mm:ss.SSS"), // Eg. 18-01-2026 14:30:45.123
    MM_dd_yyyy_HH_mm("MM-dd-yyyy HH:mm"), // Eg. 01-18-2026 14:30
    MM_dd_yyyy_HH_mm_ss("MM-dd-yyyy HH:mm:ss"), // Eg. 01-18-2026 14:30:45
    yyyy_MM_dd_HH_mm("yyyy-MM-dd HH:mm"), // Eg. 2026-01-18 14:30
    yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"), // Eg. 2026-01-18 14:30:45
    yyyy_MM_dd_HH_mm_ss_SSS("yyyy-MM-dd HH:mm:ss.SSS"), // Eg. 2026-01-18 14:30:45.123
    yyyy_MM_dd_HH_mm_ss_SSSSSS("yyyy-MM-dd HH:mm:ss.SSSSSS"), // Eg. 2026-01-18 14:30:45.123456

    // DateTime formats with slash separator (24-hour)
    dd_MM_yyyy_HH_mm_slash("dd/MM/yyyy HH:mm"), // Eg. 18/01/2026 14:30
    dd_MM_yyyy_HH_mm_ss_slash("dd/MM/yyyy HH:mm:ss"), // Eg. 18/01/2026 14:30:45
    MM_dd_yyyy_HH_mm_slash("MM/dd/yyyy HH:mm"), // Eg. 01/18/2026 14:30
    MM_dd_yyyy_HH_mm_ss_slash("MM/dd/yyyy HH:mm:ss"), // Eg. 01/18/2026 14:30:45
    yyyy_MM_dd_HH_mm_slash("yyyy/MM/dd HH:mm"), // Eg. 2026/01/18 14:30
    yyyy_MM_dd_HH_mm_ss_slash("yyyy/MM/dd HH:mm:ss"), // Eg. 2026/01/18 14:30:45

    // DateTime formats with dot separator (24-hour)
    dd_MM_yyyy_HH_mm_dot("dd.MM.yyyy HH:mm"), // Eg. 18.01.2026 14:30
    dd_MM_yyyy_HH_mm_ss_dot("dd.MM.yyyy HH:mm:ss"), // Eg. 18.01.2026 14:30:45
    yyyy_MM_dd_HH_mm_dot("yyyy.MM.dd HH:mm"), // Eg. 2026.01.18 14:30
    yyyy_MM_dd_HH_mm_ss_dot("yyyy.MM.dd HH:mm:ss"), // Eg. 2026.01.18 14:30:45

    // DateTime formats with 12-hour time
    dd_MM_yyyy_hh_mm_a("dd-MM-yyyy hh:mm a"), // Eg. 18-01-2026 02:30 PM
    dd_MM_yyyy_hh_mm_ss_a("dd-MM-yyyy hh:mm:ss a"), // Eg. 18-01-2026 02:30:45 PM
    MM_dd_yyyy_hh_mm_a("MM-dd-yyyy hh:mm a"), // Eg. 01-18-2026 02:30 PM
    MM_dd_yyyy_hh_mm_ss_a("MM-dd-yyyy hh:mm:ss a"), // Eg. 01-18-2026 02:30:45 PM
    yyyy_MM_dd_hh_mm_a("yyyy-MM-dd hh:mm a"), // Eg. 2026-01-18 02:30 PM
    yyyy_MM_dd_hh_mm_ss_a("yyyy-MM-dd hh:mm:ss a"), // Eg. 2026-01-18 02:30:45 PM
    dd_MM_yyyy_hh_mm_a_slash("dd/MM/yyyy hh:mm a"), // Eg. 18/01/2026 02:30 PM
    MM_dd_yyyy_hh_mm_a_slash("MM/dd/yyyy hh:mm a"), // Eg. 01/18/2026 02:30 PM
    yyyy_MM_dd_hh_mm_a_slash("yyyy/MM/dd hh:mm a"), // Eg. 2026/01/18 02:30 PM

    // DateTime with month names (24-hour)
    dd_MMM_yyyy_HH_mm("dd MMM yyyy HH:mm"), // Eg. 18 Jan 2026 14:30
    dd_MMM_yyyy_HH_mm_ss("dd MMM yyyy HH:mm:ss"), // Eg. 18 Jan 2026 14:30:45
    dd_MMMM_yyyy_HH_mm("dd MMMM yyyy HH:mm"), // Eg. 18 January 2026 14:30
    dd_MMMM_yyyy_HH_mm_ss("dd MMMM yyyy HH:mm:ss"), // Eg. 18 January 2026 14:30:45
    MMM_dd_yyyy_HH_mm("MMM dd, yyyy HH:mm"), // Eg. Jan 18, 2026 14:30
    MMM_dd_yyyy_HH_mm_ss("MMM dd, yyyy HH:mm:ss"), // Eg. Jan 18, 2026 14:30:45
    MMMM_dd_yyyy_HH_mm("MMMM dd, yyyy HH:mm"), // Eg. January 18, 2026 14:30
    MMMM_dd_yyyy_HH_mm_ss("MMMM dd, yyyy HH:mm:ss"), // Eg. January 18, 2026 14:30:45
    yyyy_MMM_dd_HH_mm("yyyy MMM dd HH:mm"), // Eg. 2026 Jan 18 14:30
    yyyy_MMM_dd_HH_mm_ss("yyyy MMM dd HH:mm:ss"), // Eg. 2026 Jan 18 14:30:45

    // DateTime with month names (12-hour)
    dd_MMM_yyyy_hh_mm_a("dd MMM yyyy hh:mm a"), // Eg. 18 Jan 2026 02:30 PM
    dd_MMM_yyyy_hh_mm_ss_a("dd MMM yyyy hh:mm:ss a"), // Eg. 18 Jan 2026 02:30:45 PM
    MMM_dd_yyyy_hh_mm_a("MMM dd, yyyy hh:mm a"), // Eg. Jan 18, 2026 02:30 PM
    MMM_dd_yyyy_hh_mm_ss_a("MMM dd, yyyy hh:mm:ss a"), // Eg. Jan 18, 2026 02:30:45 PM
    dd_MMMM_yyyy_hh_mm_a("dd MMMM yyyy hh:mm a"), // Eg. 18 January 2026 02:30 PM
    MMMM_dd_yyyy_hh_mm_a("MMMM dd, yyyy hh:mm a"), // Eg. January 18, 2026 02:30 PM

    // DateTime with day of week (24-hour)
    EEEE_dd_MMMM_yyyy_HH_mm("EEEE, dd MMMM yyyy HH:mm"), // Eg. Sunday, 18 January 2026 14:30
    EEEE_dd_MMMM_yyyy_HH_mm_ss(
        "EEEE, dd MMMM yyyy HH:mm:ss"
    ), // Eg. Sunday, 18 January 2026 14:30:45
    EEE_dd_MMM_yyyy_HH_mm("EEE, dd MMM yyyy HH:mm"), // Eg. Sun, 18 Jan 2026 14:30
    EEE_dd_MMM_yyyy_HH_mm_ss("EEE, dd MMM yyyy HH:mm:ss"), // Eg. Sun, 18 Jan 2026 14:30:45
    E_dd_MMM_yyyy_HH_mm("E, dd MMM yyyy HH:mm"), // Eg. Sun, 18 Jan 2026 14:30
    EEEE_dd_MM_yyyy_HH_mm("EEEE, dd-MM-yyyy HH:mm"), // Eg. Sunday, 18-01-2026 14:30
    EEE_dd_MM_yyyy_HH_mm("EEE, dd-MM-yyyy HH:mm"), // Eg. Sun, 18-01-2026 14:30

    // DateTime with day of week (12-hour)
    EEEE_dd_MMMM_yyyy_hh_mm_a(
        "EEEE, dd MMMM yyyy hh:mm a"
    ), // Eg. Sunday, 18 January 2026 02:30 PM
    EEE_dd_MMM_yyyy_hh_mm_a("EEE, dd MMM yyyy hh:mm a"), // Eg. Sun, 18 Jan 2026 02:30 PM
    EEEE_MMM_dd_yyyy_hh_mm_a("EEEE, MMM dd, yyyy hh:mm a"), // Eg. Sunday, Jan 18, 2026 02:30 PM
    EEE_MMM_dd_yyyy_hh_mm_a("EEE, MMM dd, yyyy hh:mm a"), // Eg. Sun, Jan 18, 2026 02:30 PM

    // ==================== ISO 8601 FORMATS ====================

    yyyy_MM_dd_T_HH_mm_ss_SSS_Z("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"), // Eg. 2026-01-18T14:30:45.123Z
    yyyy_MM_dd_T_HH_mm_ss_Z("yyyy-MM-dd'T'HH:mm:ss'Z'"), // Eg. 2026-01-18T14:30:45Z
    yyyy_MM_dd_T_HH_mm_ss("yyyy-MM-dd'T'HH:mm:ss"), // Eg. 2026-01-18T14:30:45
    yyyy_MM_dd_T_HH_mm("yyyy-MM-dd'T'HH:mm"), // Eg. 2026-01-18T14:30
    yyyy_MM_dd_T_HH_mm_Z("yyyy-MM-dd'T'HH:mm'Z'"), // Eg. 2026-01-18T14:30Z

    // ISO 8601 with timezone offset
    yyyy_MM_dd_T_HH_mm_ss_SSSXXX(
        "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    ), // Eg. 2026-01-18T14:30:45.123+05:30
    yyyy_MM_dd_T_HH_mm_ss_XXX("yyyy-MM-dd'T'HH:mm:ssXXX"), // Eg. 2026-01-18T14:30:45+05:30
    yyyy_MM_dd_T_HH_mm_XXX("yyyy-MM-dd'T'HH:mmXXX"), // Eg. 2026-01-18T14:30+05:30
    yyyy_MM_dd_T_HH_mm_ss_SSSSSSXXX(
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"
    ), // Eg. 2026-01-18T14:30:45.123456+05:30
    yyyy_MM_dd_T_HH_mm_ss_SSSSSSSSSXXX(
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSXXX"
    ), // Eg. 2026-01-18T14:30:45.123456789+05:30

    // ISO 8601 basic format (no separators)
    yyyyMMdd_T_HHmmss("yyyyMMdd'T'HHmmss"), // Eg. 20260118T143045
    yyyyMMdd_T_HHmmssSSS("yyyyMMdd'T'HHmmssSSS"), // Eg. 20260118T143045123
    yyyyMMdd_T_HHmmss_Z("yyyyMMdd'T'HHmmss'Z'"), // Eg. 20260118T143045Z

    // ==================== RFC FORMATS ====================

    // RFC 2822 / RFC 822 (Email)
    EEE_dd_MMM_yyyy_HH_mm_ss_Z(
        "EEE, dd MMM yyyy HH:mm:ss Z"
    ), // Eg. Sun, 18 Jan 2026 14:30:45 +0530
    EEE_dd_MMM_yyyy_HH_mm_ss_z(
        "EEE, dd MMM yyyy HH:mm:ss z"
    ), // Eg. Sun, 18 Jan 2026 14:30:45 IST
    E_dd_MMM_yyyy_HH_mm_ss_Z(
        "E, dd MMM yyyy HH:mm:ss Z"
    ), // Eg. Sun, 18 Jan 2026 14:30:45 +0530

    // RFC 3339 (with space instead of T)
    yyyy_MM_dd_HH_mm_ss_SSSXXX(
        "yyyy-MM-dd HH:mm:ss.SSSXXX"
    ), // Eg. 2026-01-18 14:30:45.123+05:30
    yyyy_MM_dd_HH_mm_ss_XXX("yyyy-MM-dd HH:mm:ssXXX"), // Eg. 2026-01-18 14:30:45+05:30
    yyyy_MM_dd_HH_mmXXX("yyyy-MM-dd HH:mmXXX"), // Eg. 2026-01-18 14:30+05:30

    // RFC 1123
    EEE_dd_MMM_yyyy_HH_mm_ss_GMT(
        "EEE, dd MMM yyyy HH:mm:ss 'GMT'"
    ), // Eg. Sun, 18 Jan 2026 14:30:45 GMT

    // ==================== COMPACT DATETIME FORMATS ====================

    yyyyMMdd_HHmmss("yyyyMMdd_HHmmss"), // Eg. 20260118_143045
    yyyyMMddHHmmss("yyyyMMddHHmmss"), // Eg. 20260118143045
    ddMMyyyy_HHmmss("ddMMyyyy_HHmmss"), // Eg. 18012026_143045
    ddMMyyyyHHmmss("ddMMyyyyHHmmss"), // Eg. 18012026143045
    yyyyMMdd_HHmm("yyyyMMdd_HHmm"), // Eg. 20260118_1430
    yyyyMMddHHmm("yyyyMMddHHmm"), // Eg. 202601181430
    yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS"), // Eg. 20260118143045123

    // ==================== DATETIME WITH TIMEZONE ====================

    // With named timezone
    dd_MM_yyyy_HH_mm_z("dd-MM-yyyy HH:mm z"), // Eg. 18-01-2026 14:30 IST
    dd_MM_yyyy_HH_mm_ss_z("dd-MM-yyyy HH:mm:ss z"), // Eg. 18-01-2026 14:30:45 IST
    yyyy_MM_dd_HH_mm_z("yyyy-MM-dd HH:mm z"), // Eg. 2026-01-18 14:30 IST
    yyyy_MM_dd_HH_mm_ss_z("yyyy-MM-dd HH:mm:ss z"), // Eg. 2026-01-18 14:30:45 IST
    dd_MM_yyyy_HH_mm_zzzz("dd-MM-yyyy HH:mm zzzz"), // Eg. 18-01-2026 14:30 Indian Standard Time
    yyyy_MM_dd_HH_mm_zzzz("yyyy-MM-dd HH:mm zzzz"), // Eg. 2026-01-18 14:30 Indian Standard Time

    // With numeric timezone offset
    dd_MM_yyyy_HH_mm_Z("dd-MM-yyyy HH:mm Z"), // Eg. 18-01-2026 14:30 +0530
    dd_MM_yyyy_HH_mm_ss_Z("dd-MM-yyyy HH:mm:ss Z"), // Eg. 18-01-2026 14:30:45 +0530
    yyyy_MM_dd_HH_mm_Z("yyyy-MM-dd HH:mm Z"), // Eg. 2026-01-18 14:30 +0530
    yyyy_MM_dd_HH_mm_ss_Z("yyyy-MM-dd HH:mm:ss Z"), // Eg. 2026-01-18 14:30:45 +0530
    dd_MM_yyyy_HH_mm_XXX("dd-MM-yyyy HH:mm XXX"), // Eg. 18-01-2026 14:30 +05:30
    dd_MM_yyyy_HH_mm_ss_XXX("dd-MM-yyyy HH:mm:ss XXX"), // Eg. 18-01-2026 14:30:45 +05:30
    yyyy_MM_dd_HH_mm_XXX("yyyy-MM-dd HH:mm XXX"), // Eg. 2026-01-18 14:30 +05:30
    dd_MM_yyyy_HH_mm_ss_SSS_XXX(
        "dd-MM-yyyy HH:mm:ss.SSS XXX"
    ), // Eg. 18-01-2026 14:30:45.123 +05:30
    yyyy_MM_dd_HH_mm_ss_SSS_XXX(
        "yyyy-MM-dd HH:mm:ss.SSS XXX"
    ), // Eg. 2026-01-18 14:30:45.123 +05:30

    // With timezone region
    yyyy_MM_dd_HH_mm_VV("yyyy-MM-dd HH:mm VV"), // Eg. 2026-01-18 14:30 Asia/Kolkata
    yyyy_MM_dd_HH_mm_ss_VV("yyyy-MM-dd HH:mm:ss VV"), // Eg. 2026-01-18 14:30:45 Asia/Kolkata
    dd_MM_yyyy_HH_mm_VV("dd-MM-yyyy HH:mm VV"), // Eg. 18-01-2026 14:30 Asia/Kolkata
    dd_MM_yyyy_HH_mm_ss_VV("dd-MM-yyyy HH:mm:ss VV"), // Eg. 18-01-2026 14:30:45 Asia/Kolkata

    // With multiple timezone formats
    yyyy_MM_dd_HH_mm_ss_XXX_z(
        "yyyy-MM-dd HH:mm:ss XXX z"
    ), // Eg. 2026-01-18 14:30:45 +05:30 IST
    yyyy_MM_dd_HH_mm_ss_XXX_VV(
        "yyyy-MM-dd HH:mm:ss XXX VV"
    ), // Eg. 2026-01-18 14:30:45 +05:30 Asia/Kolkata

    // ==================== UNIX EPOCH TIMESTAMPS ====================

    // Note: These are typically stored as Long/String numbers, not patterns
    // Included here for completeness with examples
    EPOCH_SECONDS("epoch_seconds"), // Eg. 1768752045 (seconds since Jan 1, 1970)
    EPOCH_MILLISECONDS(
        "epoch_milliseconds"
    ), // Eg. 1768752045123 (milliseconds since Jan 1, 1970)
    EPOCH_MICROSECONDS(
        "epoch_microseconds"
    ), // Eg. 1768752045123456 (microseconds since Jan 1, 1970)
    EPOCH_NANOSECONDS(
        "epoch_nanoseconds"
    ), // Eg. 1768752045123456789 (nanoseconds since Jan 1, 1970)

    // ==================== RELATIVE TIME FORMATS ====================

    // Duration formats
    HH_mm_ss_for_duration("HH:mm:ss"), // Eg. 02:30:45 (duration)
    mm_ss_for_duration("mm:ss"), // Eg. 30:45 (duration)
    HH_mm_for_duration("HH:mm"), // Eg. 02:30 (duration)

    // ==================== COMMON TIMEZONE SPECIFIC FORMATS ====================

    // UTC formats
    yyyy_MM_dd_HH_mm_ss_UTC("yyyy-MM-dd HH:mm:ss 'UTC'"), // Eg. 2026-01-18 09:00:45 UTC
    dd_MM_yyyy_HH_mm_UTC("dd-MM-yyyy HH:mm 'UTC'"), // Eg. 18-01-2026 09:00 UTC
    yyyy_MM_dd_T_HH_mm_ss_UTC("yyyy-MM-dd'T'HH:mm:ss 'UTC'"), // Eg. 2026-01-18T09:00:45 UTC

    // GMT formats
    yyyy_MM_dd_HH_mm_ss_GMT("yyyy-MM-dd HH:mm:ss 'GMT'"), // Eg. 2026-01-18 09:00:45 GMT
    dd_MM_yyyy_HH_mm_GMT("dd-MM-yyyy HH:mm 'GMT'"), // Eg. 18-01-2026 09:00 GMT

    // IST formats
    yyyy_MM_dd_HH_mm_ss_IST("yyyy-MM-dd HH:mm:ss 'IST'"), // Eg. 2026-01-18 14:30:45 IST
    dd_MM_yyyy_HH_mm_IST("dd-MM-yyyy HH:mm 'IST'"), // Eg. 18-01-2026 14:30 IST

    // PST/PDT formats
    yyyy_MM_dd_HH_mm_ss_PST("yyyy-MM-dd HH:mm:ss 'PST'"), // Eg. 2026-01-18 01:00:45 PST
    MM_dd_yyyy_hh_mm_a_PST("MM/dd/yyyy hh:mm a 'PST'"), // Eg. 01/18/2026 01:00 AM PST

    // EST/EDT formats
    yyyy_MM_dd_HH_mm_ss_EST("yyyy-MM-dd HH:mm:ss 'EST'"), // Eg. 2026-01-18 04:00:45 EST
    MM_dd_yyyy_hh_mm_a_EST("MM/dd/yyyy hh:mm a 'EST'"), // Eg. 01/18/2026 04:00 AM EST

    // CST formats
    yyyy_MM_dd_HH_mm_ss_CST("yyyy-MM-dd HH:mm:ss 'CST'"), // Eg. 2026-01-18 03:00:45 CST
    MM_dd_yyyy_hh_mm_a_CST("MM/dd/yyyy hh:mm a 'CST'"), // Eg. 01/18/2026 03:00 AM CST

    // JST formats
    yyyy_MM_dd_HH_mm_ss_JST("yyyy-MM-dd HH:mm:ss 'JST'"), // Eg. 2026-01-18 18:00:45 JST

    // AEST/AEDT formats
    yyyy_MM_dd_HH_mm_ss_AEST("yyyy-MM-dd HH:mm:ss 'AEST'"), // Eg. 2026-01-18 20:00:45 AEST
    dd_MM_yyyy_HH_mm_AEST("dd/MM/yyyy HH:mm 'AEST'"), // Eg. 18/01/2026 20:00 AEST

    // ==================== LOCALIZED FORMATS ====================

    // Chinese formats
    yyyy_年_MM_月_dd_日("yyyy'年'MM'月'dd'日'"), // Eg. 2026年01月18日
    yyyy_年_MM_月_dd_日_HH_mm("yyyy'年'MM'月'dd'日' HH:mm"), // Eg. 2026年01月18日 14:30
    yyyy_年_MM_月_dd_日_HH_mm_ss("yyyy'年'MM'月'dd'日' HH:mm:ss"), // Eg. 2026年01月18日 14:30:45

    // Japanese formats
    yyyy年MM月dd日("yyyy'年'MM'月'dd'日'"), // Eg. 2026年01月18日
    yyyy年MM月dd日_HH時mm分("yyyy'年'MM'月'dd'日' HH'時'mm'分'"), // Eg. 2026年01月18日 14時30分
    yyyy年MM月dd日_HH時mm分ss秒("yyyy'年'MM'月'dd'日' HH'時'mm'分'ss'秒'"), // Eg. 2026年01月18日 14時30分45秒

    // Korean formats
    yyyy_년_MM_월_dd_일("yyyy'년' MM'월' dd'일'"), // Eg. 2026년 01월 18일
    yyyy_년_MM_월_dd_일_HH_시_mm_분("yyyy'년' MM'월' dd'일' HH'시' mm'분'"), // Eg. 2026년 01월 18일 14시 30분

    // Arabic formats
    dd_MM_yyyy_arabic("dd/MM/yyyy"), // Eg. ١٨/٠١/٢٠٢٦ (RTL)
    yyyy_MM_dd_HH_mm_arabic("yyyy-MM-dd HH:mm"), // Eg. ٢٠٢٦-٠١-١٨ ١٤:٣٠

    // French formats
    dd_MM_yyyy_french("dd/MM/yyyy"), // Eg. 18/01/2026
    dd_MMMM_yyyy_french("dd MMMM yyyy"), // Eg. 18 janvier 2026
    EEE_dd_MMM_yyyy_HH_mm_french("EEE dd MMM yyyy HH:mm"), // Eg. dim. 18 janv. 2026 14:30

    // German formats
    dd_MM_yyyy_german("dd.MM.yyyy"), // Eg. 18.01.2026
    dd_MMMM_yyyy_german("dd. MMMM yyyy"), // Eg. 18. Januar 2026
    EEE_dd_MMM_yyyy_HH_mm_german("EEE., dd. MMM yyyy HH:mm"), // Eg. So., 18. Jan. 2026 14:30

    // Spanish formats
    dd_MM_yyyy_spanish("dd/MM/yyyy"), // Eg. 18/01/2026
    dd_de_MMMM_de_yyyy("dd 'de' MMMM 'de' yyyy"), // Eg. 18 de enero de 2026
    EEE_dd_MMM_yyyy_spanish("EEE, dd MMM yyyy"), // Eg. dom, 18 ene 2026

    // Italian formats
    dd_MM_yyyy_italian("dd/MM/yyyy"), // Eg. 18/01/2026
    dd_MMMM_yyyy_italian("dd MMMM yyyy"), // Eg. 18 gennaio 2026

    // Portuguese formats
    dd_MM_yyyy_portuguese("dd/MM/yyyy"), // Eg. 18/01/2026
    dd_de_MMMM_de_yyyy_portuguese("dd 'de' MMMM 'de' yyyy"), // Eg. 18 de janeiro de 2026

    // Russian formats
    dd_MM_yyyy_russian("dd.MM.yyyy"), // Eg. 18.01.2026
    dd_MMMM_yyyy_г("dd MMMM yyyy 'г.'"), // Eg. 18 января 2026 г.

    // ==================== DATABASE & SYSTEM FORMATS ====================

    // MySQL DATETIME
    MYSQL_DATETIME("yyyy-MM-dd HH:mm:ss"), // Eg. 2026-01-18 14:30:45
    MYSQL_DATE("yyyy-MM-dd"), // Eg. 2026-01-18
    MYSQL_TIME("HH:mm:ss"), // Eg. 14:30:45
    MYSQL_TIMESTAMP("yyyy-MM-dd HH:mm:ss"), // Eg. 2026-01-18 14:30:45

    // PostgreSQL
    POSTGRESQL_TIMESTAMP("yyyy-MM-dd HH:mm:ss.SSSSSS"), // Eg. 2026-01-18 14:30:45.123456
    POSTGRESQL_TIMESTAMPTZ(
        "yyyy-MM-dd HH:mm:ss.SSSSSSXXX"
    ), // Eg. 2026-01-18 14:30:45.123456+05:30

    // MongoDB
    MONGODB_DATE("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"), // Eg. 2026-01-18T14:30:45.123Z (ISODate)

    // Oracle
    ORACLE_DATE("dd-MMM-yy"), // Eg. 18-JAN-26
    ORACLE_TIMESTAMP("dd-MMM-yy HH.mm.ss.SSSSSSSSS"), // Eg. 18-JAN-26 14.30.45.123456789

    // SQLite
    SQLITE_DATETIME("yyyy-MM-dd HH:mm:ss"), // Eg. 2026-01-18 14:30:45

    // Microsoft SQL Server
    MSSQL_DATETIME("yyyy-MM-dd HH:mm:ss.SSS"), // Eg. 2026-01-18 14:30:45.123
    MSSQL_DATETIME2("yyyy-MM-dd HH:mm:ss.SSSSSSS"), // Eg. 2026-01-18 14:30:45.1234567

    // ==================== WEB & API FORMATS ====================

    // Cookie expiry (RFC 6265)
    COOKIE_EXPIRY("EEE, dd-MMM-yyyy HH:mm:ss 'GMT'"), // Eg. Sun, 18-Jan-2026 14:30:45 GMT

    // HTTP header (Last-Modified, Date)
    HTTP_HEADER_DATE("EEE, dd MMM yyyy HH:mm:ss 'GMT'"), // Eg. Sun, 18 Jan 2026 14:30:45 GMT

    // RSS/Atom feeds (RFC 822)
    RSS_PUBDATE("EEE, dd MMM yyyy HH:mm:ss Z"), // Eg. Sun, 18 Jan 2026 14:30:45 +0530

    // W3C DTF (XML, XHTML)
    W3C_DTF("yyyy-MM-dd'T'HH:mm:ssXXX"), // Eg. 2026-01-18T14:30:45+05:30

    // JSON API common format
    JSON_API_ISO("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"), // Eg. 2026-01-18T14:30:45.123Z

    // ==================== FILE NAMING FORMATS ====================

    FILE_DATETIME("yyyyMMdd_HHmmss"), // Eg. 20260118_143045
    FILE_DATE("yyyyMMdd"), // Eg. 20260118
    FILE_DATETIME_MS("yyyyMMdd_HHmmssSSS"), // Eg. 20260118_143045123
    FILE_YEAR_MONTH("yyyyMM"), // Eg. 202601
    LOG_TIMESTAMP("yyyy-MM-dd HH:mm:ss.SSS"), // Eg. 2026-01-18 14:30:45.123
    BACKUP_TIMESTAMP("yyyyMMdd-HHmmss"), // Eg. 20260118-143045

    // ==================== CONVERSATIONAL & DISPLAY FORMATS ====================

    CHAT_TIMESTAMP("hh:mm a"), // Eg. 02:30 PM
    CHAT_TODAY("'Today at' hh:mm a"), // Eg. Today at 02:30 PM
    CHAT_YESTERDAY("'Yesterday at' hh:mm a"), // Eg. Yesterday at 02:30 PM
    CHAT_FULL("EEE, MMM dd 'at' hh:mm a"), // Eg. Sun, Jan 18 at 02:30 PM
    NOTIFICATION_TIME("h:mm a"), // Eg. 2:30 PM
    RELATIVE_TIME_SHORT("MMM dd"), // Eg. Jan 18
    RELATIVE_TIME_YEAR("MMM dd, yyyy"), // Eg. Jan 18, 2026

    // ==================== CALENDAR FORMATS ====================

    CALENDAR_HEADER("MMMM yyyy"), // Eg. January 2026
    CALENDAR_DAY("EEEE, MMMM dd"), // Eg. Sunday, January 18
    CALENDAR_EVENT_TIME("h:mm a"), // Eg. 2:30 PM
    CALENDAR_EVENT_DATETIME("EEEE, MMM dd 'at' h:mm a"), // Eg. Sunday, Jan 18 at 2:30 PM
    CALENDAR_MONTH_DAY("MMM dd"), // Eg. Jan 18

    // ==================== SPECIAL USE CASE FORMATS ====================

    // Credit card expiry
    CREDIT_CARD_EXPIRY("MM/yy"), // Eg. 01/26
    CREDIT_CARD_EXPIRY_FULL("MM/yyyy"), // Eg. 01/2026

    // Age calculation
    BIRTHDATE("dd/MM/yyyy"), // Eg. 18/01/1990
    BIRTHDATE_US("MM/dd/yyyy"), // Eg. 01/18/1990

    // Financial year
    FINANCIAL_YEAR("yyyy-yy"), // Eg. 2025-26
    QUARTER_YEAR("'Q'Q yyyy"), // Eg. Q1 2026

    // Version timestamps
    VERSION_TIMESTAMP("yyyyMMddHHmmss"), // Eg. 20260118143045

    // Git commit timestamp
    GIT_COMMIT_DATE("EEE MMM dd HH:mm:ss yyyy Z"), // Eg. Sun Jan 18 14:30:45 2026 +0530

    // ==================== MEDICAL & SCIENTIFIC FORMATS ====================

    MEDICAL_RECORD_DATE("dd-MMM-yyyy"), // Eg. 18-Jan-2026
    LAB_REPORT_DATETIME("dd/MM/yyyy HH:mm"), // Eg. 18/01/2026 14:30
    PRESCRIPTION_DATE("dd MMM yyyy"), // Eg. 18 Jan 2026

    // ==================== LEGAL & OFFICIAL FORMATS ====================

    LEGAL_DATE_US("MMMM dd, yyyy"), // Eg. January 18, 2026
    LEGAL_DATE_UK("dd MMMM yyyy"), // Eg. 18 January 2026
    CONTRACT_DATE("dd 'day of' MMMM yyyy"), // Eg. 18 day of January 2026
    NOTARY_DATE("'the' dd 'day of' MMMM, yyyy"), // Eg. the 18 day of January, 2026

    // ==================== AVIATION FORMATS ====================

    AVIATION_DATE("ddMMMyy"), // Eg. 18JAN26
    FLIGHT_SCHEDULE("ddMMM HHmm"), // Eg. 18JAN 1430
    AVIATION_DATETIME("ddMMMyyyy HHmm 'UTC'"), // Eg. 18JAN2026 0900 UTC

    // ==================== MARITIME FORMATS ====================

    MARITIME_DATE("dd-MMM-yyyy"), // Eg. 18-JAN-2026
    SHIP_LOG("ddMMMMyyyy HHmm 'UTC'"), // Eg. 18JANUARY2026 0900 UTC

    // ==================== MILITARY FORMATS (DTG - Date Time Group) ====================

    MILITARY_DTG("ddHHmm'Z' MMM yy"), // Eg. 180900Z JAN 26
    MILITARY_FULL_DTG("ddHHmmss'Z' MMM yyyy"), // Eg. 180900450Z JAN 2026

    // ==================== ASTRONOMICAL FORMATS ====================

    JULIAN_DATE("julian_date"), // Eg. 2459958.10416667 (days since Jan 1, 4713 BC)
    MODIFIED_JULIAN_DATE(
        "modified_julian_date"
    ), // Eg. 59957.10416667 (days since Nov 17, 1858)

    // ==================== ANCIENT & HISTORICAL FORMATS ====================

    ROMAN_DATE("roman_date"), // Eg. a.d. XVIII Kal. Feb. MMXXVI
    HEBREW_DATE("hebrew_date"), // Eg. כ״ה בטבת תשפ״ו
    ISLAMIC_HIJRI("islamic_hijri"), // Eg. 18 Rajab 1447 AH
    PERSIAN_DATE("persian_date"), // Eg. ۲۸ دی ۱۴۰۴
    ETHIOPIAN_DATE("ethiopian_date"), // Eg. 9 Terr 2018 EC

    // ==================== SPECIALIZED TIMESTAMP FORMATS ====================

    // Excel/Lotus serial date
    EXCEL_SERIAL_DATE("excel_serial"), // Eg. 46023 (days since Jan 1, 1900)

    // Swatch Internet Time
    SWATCH_BEAT("swatch_beat"), // Eg. @604 (no timezone, 1000 beats per day)

    // TAI (International Atomic Time)
    TAI_TIMESTAMP("tai_timestamp"), // Eg. 4818432682 (seconds since Jan 1, 1958)

    // GPS Time
    GPS_WEEK_SECONDS("gps_week_seconds"), // Eg. Week: 2294, Seconds: 52245

    // ==================== AMBIGUOUS FORMATS (Use with caution) ====================

    AMBIGUOUS_US_UK("MM/dd/yyyy"), // US: 01/18/2026, UK interprets as 01/18/2026
    AMBIGUOUS_SHORT("dd/MM/yy"), // Could be confused with MM/dd/yy

    // ==================== DATETIME WITH AM/PM VARIANTS ====================

    hh_mm_ss_a_lowercase("hh:mm:ss a"), // Eg. 02:30:45 pm (lowercase)
    hh_mm_ss_A_uppercase("hh:mm:ss A"), // Eg. 02:30:45 PM (uppercase)
    hh_mm_ss_a_no_space("hh:mm:ssa"), // Eg. 02:30:45pm

    // ==================== TIMESTAMP WITH FRACTIONAL SECONDS ====================

    TIMESTAMP_MILLIS_1("yyyy-MM-dd HH:mm:ss.S"), // Eg. 2026-01-18 14:30:45.1
    TIMESTAMP_MILLIS_2("yyyy-MM-dd HH:mm:ss.SS"), // Eg. 2026-01-18 14:30:45.12
    TIMESTAMP_MILLIS_3("yyyy-MM-dd HH:mm:ss.SSS"), // Eg. 2026-01-18 14:30:45.123
    TIMESTAMP_MILLIS_4("yyyy-MM-dd HH:mm:ss.SSSS"), // Eg. 2026-01-18 14:30:45.1234
    TIMESTAMP_MILLIS_5("yyyy-MM-dd HH:mm:ss.SSSSS"), // Eg. 2026-01-18 14:30:45.12345
    TIMESTAMP_MILLIS_6("yyyy-MM-dd HH:mm:ss.SSSSSS"), // Eg. 2026-01-18 14:30:45.123456
    TIMESTAMP_MILLIS_9("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"), // Eg. 2026-01-18 14:30:45.123456789

    // ==================== ADDITIONAL ISO 8601 VARIATIONS ====================

    ISO_WEEK_DATE("YYYY-'W'ww-e"), // Eg. 2026-W03-7
    ISO_ORDINAL_DATE("yyyy-DDD"), // Eg. 2026-018
    ISO_BASIC_DATE("yyyyMMdd"), // Eg. 20260118
    ISO_BASIC_TIME("HHmmss"), // Eg. 143045
    ISO_BASIC_DATETIME("yyyyMMdd'T'HHmmss"), // Eg. 20260118T143045
    ISO_EXTENDED_DATE("yyyy-MM-dd"), // Eg. 2026-01-18
    ISO_EXTENDED_TIME("HH:mm:ss"), // Eg. 14:30:45
    ISO_EXTENDED_DATETIME("yyyy-MM-dd'T'HH:mm:ss"), // Eg. 2026-01-18T14:30:45

    // ==================== CRON/SCHEDULING FORMATS ====================

    CRON_DATETIME("ss mm HH dd MM '?' yyyy"), // Eg. 45 30 14 18 01 ? 2026
    QUARTZ_CRON("ss mm HH dd MM '?' yyyy"), // Eg. 45 30 14 18 01 ? 2026

    // ==================== BLOCKCHAIN TIMESTAMPS ====================

    BITCOIN_BLOCK_TIME("epoch_seconds"), // Eg. 1768752045 (Unix timestamp)
    ETHEREUM_BLOCK_TIME("epoch_seconds"), // Eg. 1768752045 (Unix timestamp)

    // ==================== ADDITIONAL TIMEZONE FORMATS ====================

    // All major world timezones
    yyyy_MM_dd_HH_mm_ss_NZDT("yyyy-MM-dd HH:mm:ss 'NZDT'"), // Eg. 2026-01-19 03:30:45 NZDT (New Zealand)
    yyyy_MM_dd_HH_mm_ss_AWST("yyyy-MM-dd HH:mm:ss 'AWST'"), // Eg. 2026-01-18 17:00:45 AWST (Western Australia)
    yyyy_MM_dd_HH_mm_ss_CET("yyyy-MM-dd HH:mm:ss 'CET'"), // Eg. 2026-01-18 10:00:45 CET (Central European)
    yyyy_MM_dd_HH_mm_ss_EET("yyyy-MM-dd HH:mm:ss 'EET'"), // Eg. 2026-01-18 11:00:45 EET (Eastern European)
    yyyy_MM_dd_HH_mm_ss_WET("yyyy-MM-dd HH:mm:ss 'WET'"), // Eg. 2026-01-18 09:00:45 WET (Western European)
    yyyy_MM_dd_HH_mm_ss_MSK("yyyy-MM-dd HH:mm:ss 'MSK'"), // Eg. 2026-01-18 12:00:45 MSK (Moscow)
    yyyy_MM_dd_HH_mm_ss_AST("yyyy-MM-dd HH:mm:ss 'AST'"), // Eg. 2026-01-18 05:00:45 AST (Atlantic)
    yyyy_MM_dd_HH_mm_ss_HST("yyyy-MM-dd HH:mm:ss 'HST'"), // Eg. 2026-01-17 23:00:45 HST (Hawaii)
    yyyy_MM_dd_HH_mm_ss_AKST("yyyy-MM-dd HH:mm:ss 'AKST'"), // Eg. 2026-01-18 00:00:45 AKST (Alaska)
    yyyy_MM_dd_HH_mm_ss_MST("yyyy-MM-dd HH:mm:ss 'MST'"), // Eg. 2026-01-18 02:00:45 MST (Mountain)
    yyyy_MM_dd_HH_mm_ss_BST("yyyy-MM-dd HH:mm:ss 'BST'"), // Eg. 2026-01-18 10:00:45 BST (British Summer)
    yyyy_MM_dd_HH_mm_ss_SAST("yyyy-MM-dd HH:mm:ss 'SAST'"), // Eg. 2026-01-18 11:00:45 SAST (South Africa)
    yyyy_MM_dd_HH_mm_ss_PKT("yyyy-MM-dd HH:mm:ss 'PKT'"), // Eg. 2026-01-18 14:00:45 PKT (Pakistan)
    yyyy_MM_dd_HH_mm_ss_BDT("yyyy-MM-dd HH:mm:ss 'BDT'"), // Eg. 2026-01-18 15:00:45 BDT (Bangladesh)
    yyyy_MM_dd_HH_mm_ss_SGT("yyyy-MM-dd HH:mm:ss 'SGT'"), // Eg. 2026-01-18 17:00:45 SGT (Singapore)
    yyyy_MM_dd_HH_mm_ss_HKT("yyyy-MM-dd HH:mm:ss 'HKT'"), // Eg. 2026-01-18 17:00:45 HKT (Hong Kong)
    yyyy_MM_dd_HH_mm_ss_KST("yyyy-MM-dd HH:mm:ss 'KST'"), // Eg. 2026-01-18 18:00:45 KST (Korea)
    yyyy_MM_dd_HH_mm_ss_BRST("yyyy-MM-dd HH:mm:ss 'BRST'"), // Eg. 2026-01-18 06:00:45 BRST (Brazil)
    yyyy_MM_dd_HH_mm_ss_ART("yyyy-MM-dd HH:mm:ss 'ART'"), // Eg. 2026-01-18 06:00:45 ART (Argentina)
    yyyy_MM_dd_HH_mm_ss_NZST("yyyy-MM-dd HH:mm:ss 'NZST'"), // Eg. 2026-01-18 22:00:45 NZST (New Zealand Standard)

    // ==================== QUARTER-BASED FORMATS ====================

    QUARTER_yyyy_Q("yyyy 'Q'Q"), // Eg. 2026 Q1
    Q_QUARTER_yyyy("'Q'Q yyyy"), // Eg. Q1 2026
    QUARTER_yyyy_Qq("yyyy'-Q'Q"), // Eg. 2026-Q1
    FY_QUARTER("'FY'yy'-Q'Q"), // Eg. FY26-Q1

    // ==================== SEMESTER/HALF-YEAR FORMATS ====================

    HALF_YEAR_1("yyyy 'H1'"), // Eg. 2026 H1
    HALF_YEAR_2("yyyy 'H2'"), // Eg. 2026 H2
    SEMESTER_yyyy_H("yyyy'-H'H"), // Eg. 2026-H1

    // ==================== ACADEMIC YEAR FORMATS ====================

    ACADEMIC_YEAR("yyyy'-'yy"), // Eg. 2025-26
    ACADEMIC_FALL("'Fall' yyyy"), // Eg. Fall 2025
    ACADEMIC_SPRING("'Spring' yyyy"), // Eg. Spring 2026
    ACADEMIC_SUMMER("'Summer' yyyy"), // Eg. Summer 2026
    ACADEMIC_WINTER("'Winter' yyyy"), // Eg. Winter 2026

    // ==================== BIWEEKLY/FORTNIGHTLY FORMATS ====================

    FORTNIGHT_yyyy_ww("yyyy 'W'ww'-'ww"), // Eg. 2026 W03-04
    BIWEEKLY("'Biweekly' ww yyyy"), // Eg. Biweekly 03 2026

    // ==================== PAYROLL FORMATS ====================

    PAYROLL_PERIOD("MMM dd '-' MMM dd, yyyy"), // Eg. Jan 01 - Jan 15, 2026
    PAY_PERIOD_END("'Period ending' MMM dd, yyyy"), // Eg. Period ending Jan 18, 2026

    // ==================== SOCIAL MEDIA FORMATS ====================

    TWITTER_TIMESTAMP("h:mm a '·' MMM d, yyyy"), // Eg. 2:30 PM · Jan 18, 2026
    FACEBOOK_TIMESTAMP("MMMM d 'at' h:mm a"), // Eg. January 18 at 2:30 PM
    INSTAGRAM_TIMESTAMP("MMMM d, yyyy"), // Eg. January 18, 2026
    LINKEDIN_TIMESTAMP("MMM dd, yyyy"), // Eg. Jan 18, 2026
    YOUTUBE_TIMESTAMP("MMM d, yyyy"), // Eg. Jan 18, 2026

    // ==================== E-COMMERCE FORMATS ====================

    ORDER_DATE("'Order placed on' MMMM dd, yyyy"), // Eg. Order placed on January 18, 2026
    DELIVERY_DATE("'Estimated delivery:' EEE, MMM dd"), // Eg. Estimated delivery: Sun, Jan 18
    SHIPPING_DATE("'Shipped on' MMM dd, yyyy"), // Eg. Shipped on Jan 18, 2026

    // ==================== WEATHER FORMATS ====================

    WEATHER_FORECAST("EEEE, MMMM dd"), // Eg. Sunday, January 18
    WEATHER_HOURLY("h a"), // Eg. 2 PM
    WEATHER_TIMESTAMP("EEE h:mm a"), // Eg. Sun 2:30 PM

    // ==================== EMAIL FORMATS ====================

    EMAIL_HEADER(
        "EEE, dd MMM yyyy HH:mm:ss Z"
    ), // Eg. Sun, 18 Jan 2026 14:30:45 +0530 (RFC 2822)
    EMAIL_DISPLAY("MMM dd, yyyy 'at' h:mm a"), // Eg. Jan 18, 2026 at 2:30 PM
    EMAIL_SHORT("MMM dd"), // Eg. Jan 18

    // ==================== BOOKING/RESERVATION FORMATS ====================

    CHECK_IN_DATE("'Check-in:' EEE, MMM dd, yyyy"), // Eg. Check-in: Sun, Jan 18, 2026
    CHECK_OUT_DATE("'Check-out:' EEE, MMM dd, yyyy"), // Eg. Check-out: Mon, Jan 19, 2026
    RESERVATION_TIME("EEEE, MMMM dd 'at' h:mm a"), // Eg. Sunday, January 18 at 2:30 PM
    BOOKING_REFERENCE("yyyyMMddHHmm"), // Eg. 202601181430

    // ==================== MEETING/CALENDAR INVITE FORMATS ====================

    MEETING_TITLE("EEEE, MMMM dd, yyyy"), // Eg. Sunday, January 18, 2026
    MEETING_TIME("h:mm a - h:mm a"), // Eg. 2:30 PM - 3:30 PM (needs two times)
    CALENDAR_INVITE(
        "EEEE, MMM dd 'from' h:mm a 'to' h:mm a"
    ), // Eg. Sunday, Jan 18 from 2:30 PM to 3:30 PM
    OUTLOOK_DATETIME("ddd M/d/yyyy h:mm a"), // Eg. Sun 1/18/2026 2:30 PM
    GOOGLE_CALENDAR(
        "EEEE, MMMM d, yyyy 'at' h:mm a"
    ), // Eg. Sunday, January 18, 2026 at 2:30 PM

    // ==================== AGE & DURATION DISPLAY ====================

    AGE_YEARS("y 'years'"), // Eg. 35 years
    DURATION_DAYS("d 'days'"), // Eg. 18 days
    DURATION_HOURS("H 'hours'"), // Eg. 14 hours
    DURATION_FULL("d 'days' H 'hours' m 'minutes'"), // Eg. 18 days 14 hours 30 minutes

    // ==================== COUNTDOWN/TIMER FORMATS ====================

    COUNTDOWN_FULL("d 'days' HH:mm:ss"), // Eg. 18 days 14:30:45
    COUNTDOWN_SHORT("HH:mm:ss"), // Eg. 14:30:45
    TIMER_DISPLAY("mm:ss"), // Eg. 30:45

    // ==================== HISTORICAL DATE FORMATS ====================

    BC_DATE("yyyy 'BC'"), // Eg. 500 BC
    AD_DATE("yyyy 'AD'"), // Eg. 2026 AD
    BCE_DATE("yyyy 'BCE'"), // Eg. 500 BCE
    CE_DATE("yyyy 'CE'"), // Eg. 2026 CE

    // ==================== CUSTOM SEPARATORS ====================

    UNDERSCORE_DATE("yyyy_MM_dd"), // Eg. 2026_01_18
    UNDERSCORE_DATETIME("yyyy_MM_dd_HH_mm_ss"), // Eg. 2026_01_18_14_30_45
    PIPE_DATE("yyyy|MM|dd"), // Eg. 2026|01|18
    COLON_DATE("yyyy:MM:dd"), // Eg. 2026:01:18
    AT_DATETIME("yyyy-MM-dd@HH:mm:ss"), // Eg. 2026-01-18@14:30:45

    // ==================== REVERSE FORMATS ====================

    REVERSE_US_DATE("yyyy/dd/MM"), // Eg. 2026/18/01
    REVERSE_FULL_DATE("yyyy, MMMM dd"), // Eg. 2026, January 18

    // ==================== MICROSOFT FORMATS ====================

    EXCEL_DATETIME("M/d/yyyy h:mm"), // Eg. 1/18/2026 2:30
    EXCEL_DATE_US("M/d/yyyy"), // Eg. 1/18/2026
    EXCEL_DATE_UK("dd/MM/yyyy"), // Eg. 18/01/2026
    ACCESS_DATETIME("M/d/yyyy h:mm:ss a"), // Eg. 1/18/2026 2:30:45 PM
    SHAREPOINT_DATE("yyyy-MM-ddTHH:mm:ssZ"), // Eg. 2026-01-18T14:30:45Z

    // ==================== APPLE/iOS FORMATS ====================

    COCOA_TIMESTAMP("cocoa_timestamp"), // Eg. 822758400 (seconds since Jan 1, 2001)
    IOS_SHORT_DATE("M/d/yy"), // Eg. 1/18/26
    IOS_MEDIUM_DATE("MMM d, yyyy"), // Eg. Jan 18, 2026
    IOS_LONG_DATE("MMMM d, yyyy"), // Eg. January 18, 2026

    // ==================== ANDROID FORMATS ====================

    ANDROID_SHORT("M/d/yy"), // Eg. 1/18/26
    ANDROID_MEDIUM("MMM d, yyyy"), // Eg. Jan 18, 2026
    ANDROID_LONG("MMMM d, yyyy"), // Eg. January 18, 2026
    ANDROID_FULL("EEEE, MMMM d, yyyy"), // Eg. Sunday, January 18, 2026

    // ==================== SYSLOG & LOG FORMATS ====================

    SYSLOG_TIMESTAMP("MMM dd HH:mm:ss"), // Eg. Jan 18 14:30:45
    APACHE_LOG("dd/MMM/yyyy:HH:mm:ss Z"), // Eg. 18/Jan/2026:14:30:45 +0530
    NGINX_LOG("dd/MMM/yyyy:HH:mm:ss Z"), // Eg. 18/Jan/2026:14:30:45 +0530
    WINDOWS_EVENT_LOG("M/d/yyyy h:mm:ss a"), // Eg. 1/18/2026 2:30:45 PM
    LINUX_SYSLOG("MMM dd HH:mm:ss"), // Eg. Jan 18 14:30:45
    JSON_LOG("yyyy-MM-dd'T'HH:mm:ss.SSSZ"), // Eg. 2026-01-18T14:30:45.123+0530

    // ==================== ADDITIONAL PRACTICAL FORMATS ====================

    INVOICE_DATE("'Invoice Date:' dd MMM yyyy"), // Eg. Invoice Date: 18 Jan 2026
    DUE_DATE("'Due Date:' MMMM dd, yyyy"), // Eg. Due Date: January 18, 2026
    EFFECTIVE_DATE("'Effective' MMMM dd, yyyy"), // Eg. Effective January 18, 2026
    EXPIRY_DATE("'Expires on' dd/MM/yyyy"), // Eg. Expires on 18/01/2026
    START_DATE("'Start Date:' yyyy-MM-dd"), // Eg. Start Date: 2026-01-18
    END_DATE("'End Date:' yyyy-MM-dd"), // Eg. End Date: 2026-01-18

    // ==================== LOCALE-SPECIFIC NUMBER FORMATS ====================

    ARABIC_NUMBERS_DATE("dd/MM/yyyy"), // Uses ١٢/٠١/٢٠٢٦ (Arabic-Indic digits)
    DEVANAGARI_NUMBERS_DATE("dd/MM/yyyy"), // Uses १८/०१/२०२६ (Devanagari digits)
    PERSIAN_NUMBERS_DATE("yyyy/MM/dd"), // Uses ۲۰۲۶/۰۱/۱۸ (Persian digits)
    ;

    /** Helper constants for common timezone IDs */
    object TimezoneIds {
        const val UTC = "UTC"
        const val GMT = "GMT"
        const val IST = "Asia/Kolkata"
        const val PST = "America/Los_Angeles"
        const val EST = "America/New_York"
        const val CST = "America/Chicago"
        const val MST = "America/Denver"
        const val JST = "Asia/Tokyo"
        const val CET = "Europe/Paris"
        const val AEST = "Australia/Sydney"
        const val NZST = "Pacific/Auckland"
        const val SGT = "Asia/Singapore"
        const val HKT = "Asia/Hong_Kong"
        const val BST = "Europe/London"
    }

    /** Helper constants for epoch reference dates */
    object EpochReferences {
        const val UNIX_EPOCH = "1970-01-01 00:00:00 UTC" // Unix/POSIX epoch
        const val EXCEL_EPOCH = "1900-01-01" // Excel for Windows
        const val EXCEL_MAC_EPOCH = "1904-01-01" // Excel for Mac (old)
        const val COCOA_EPOCH = "2001-01-01 00:00:00 UTC" // Apple Cocoa/iOS
        const val GPS_EPOCH = "1980-01-06 00:00:00 UTC" // GPS time
        const val NTP_EPOCH = "1900-01-01 00:00:00 UTC" // Network Time Protocol
        const val JULIAN_EPOCH = "4713-01-01 12:00:00 UTC BC" // Julian Date
        const val MODIFIED_JULIAN_EPOCH = "1858-11-17 00:00:00 UTC" // Modified Julian Date
        const val TAI_EPOCH = "1958-01-01 00:00:00 UTC" // International Atomic Time
    }
}
