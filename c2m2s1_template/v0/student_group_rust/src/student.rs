#[derive(Clone, Debug)]
pub struct Student {
    pub name: String,
    pub experience: i32,
    pub language: String,
    pub jobTitle: String,
    pub availableTimeSlots: Vec<i8>,
}

impl Student {
    pub fn new(
        name: &str,
        experience: i32,
        language: &str,
        job_title: &str,
        available_time_slots: Vec<i8>,
    ) -> Student {
        Student {
            name: name.to_string(),
            experience: experience,
            language: language.to_string(),
            jobTitle: job_title.to_string(),
            availableTimeSlots: available_time_slots,
        }
    }
}
