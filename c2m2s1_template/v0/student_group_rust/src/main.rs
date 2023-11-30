mod group;
mod student;

use student::Student;

fn main() {
    let students = parseStudentData("student.data").unwrap();

    // first cut
    let groupStrategy = group::LangBasedGroupStrategy::new();

    groupStrategy.group(students).unwrap(); // can't call
}

type ParseErr = String;

fn parseStudentData(s: &str) -> Result<Vec<Student>, ParseErr> {
    Ok(vec![Student::new(
        "Eric",
        1,
        "Go",
        "Backend Engineer",
        [
            true, false, false, false, false, false, false, false, false, false, false, false,
            false,
        ],
    )])
}
