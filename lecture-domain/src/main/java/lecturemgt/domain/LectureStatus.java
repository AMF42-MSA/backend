package lecturemgt.domain;

public enum LectureStatus {
    READY,  // 등럭
    OPENED, //수강신청 가능
    CLOSED, // 강의 폐강(수강신청 부족 또는 경매 x)
    STUDING,  // 수강중
    END,  // 종강
}
