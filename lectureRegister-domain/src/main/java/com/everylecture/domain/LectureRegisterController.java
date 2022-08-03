package com.everylecture.domain;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureRegisterController {

  @Autowired
  LectureRegisterRepository lectureCategoryRepository;

  @RequestMapping(value="lectureeRegister/registerCategory", method=RequestMethod.GET)
  public Long registerLectureRegisterGet(@RequestParam String RegisterName) {
    Long result = Long.valueOf(-1);

  //System.out.println("************GET******************** categoryName: " + categoryName);
    //Optional<LectureRegister> lectureRegister = lectureCategoryRepository.findByRegisterName(RegisterName);
    //if(lectureRegister.isPresent()) {
      // 이미 해당 카테고리명 존재할 경우, return -1
    //  return result;
    //}

        // 카테고리 등록
    //LectureRegister newLectureCategory = new LectureRegister();
    //newLectureCategory.setRegisterName(RegisterName);
    //result = lectureCategoryRepository.save(newLectureCategory).getRegisterId();

    return result;
  }

  // 강의신청 전체조회
  //@RequestMapping(value = "lectureRegister/searchAll")
  //public List<LectureRegister> searchAllLectureRegister() {

    //return lectureCategoryRepository.findAll();

  //}
}
