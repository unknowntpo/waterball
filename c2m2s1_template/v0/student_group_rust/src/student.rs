pub struct Student {
    name: String,
    experience: i32,
    language: String,
    jobTitle: String,
    availableTimeSlots: [bool; 13],
}

impl Student {
    pub fn new(
        name: &str,
        experience: i32,
        language: &str,
        job_title: &str,
        available_time_slots: [bool; 13],
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
