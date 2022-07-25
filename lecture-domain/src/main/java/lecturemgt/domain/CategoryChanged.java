package lecturemgt.domain;

import lecturemgt.infra.AbstractEvent;

public class CategoryChanged extends AbstractEvent {
    String jobType;
        public String getJobType() {
            return jobType;
        }
        public void setJobType(String jobType) {
            this.jobType = jobType;
        }

    Long id;
        public Long getId() {
            return id;
        }
        public void setId(Long id) {this.id = id;}

    String title;
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }


}
