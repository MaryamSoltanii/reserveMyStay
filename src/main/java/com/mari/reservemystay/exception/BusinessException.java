package com.mari.reservemystay.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    public static final String BASE_BUNDLE_PATH = "messages";

    private final String key;

    public BusinessException(String key) {
        super(key);
        this.key = key;
    }

    public static final String RES_ROOM_NOT_FOUND_EXCEPTION = "res.room.not.found.exception";
    public static final String RES_PERSON_NOT_FOUND_EXCEPTION = "res.person.not.found.exception";
    public static final String RES_RESERVATION_NOT_FOUND = "res.reservation.not.found";
    public static final String RES_ROOM_RESERVED = "res.room.reserved";
    public static final String RES_RESERVATION_CANCEL_ERROR = "res.reservation.cancel.error";
    public static final String USR_NOT_ACTIVE = "usr.not.active";
    public static final String NOT_VALID_OTP = "not.valid.otp";
    public static final String USR_NOT_VALID = "usr.not.valid";
    public static final String RED_RES_NOT_FOUND = "red.res.not.found";
    public static final String RED_PRS_NOT_FOUND = "red.prs.not.found";
    public static final String PRS_NOT_FOUND = "prs.not.found";
    public static final String HOA_HTL_NOT_FOUND = "hoa.htl.not.found";
    public static final String HTL_LOC_NOT_FOUND = "htl.loc.not.found";
    public static final String HTL_NOT_FOUND = "htl.not.found";
    public static final String ROM_HTL_NOT_FOUND = "rom.htl.not.found";
}