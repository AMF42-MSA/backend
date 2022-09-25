package everylecture.lecturemgt.domain;

public enum LectureStatus {
    OPEN_REGISTER, //등록,수강신청 가능
    OPEN_AUTION, //경매가능 (수강 최소인원 초과)
    CLOSED, // 강의 폐강(수강신청 부족 또는 경매 x)
    END,  // 종강
}
