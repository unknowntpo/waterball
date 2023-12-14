mod group;
mod student;

use group::{GroupStrategy, LangBasedGroupStrategy};
use std::fs::File;
use std::io::{Read, Result as IOResult};
use std::rc::Rc;
use student::Student;

fn main() {
    let content = read_file("student.data").unwrap();
    let students = parse_student_data(content).unwrap();

    let groupStrategy: Rc<dyn GroupStrategy> = Rc::new(LangBasedGroupStrategy::new());
    let groups = groupStrategy.group(students.clone()).unwrap(); // can't call
    dbg!("groups: {:?}", &groups[0]);

    // let groupStrategy2: Rc<dyn GroupStrategy> = Rc::new(TimeSlotsBasedGroupStrategy::new());
    // let groups = groupStrategy2.group(students.clone()).unwrap(); // can't call
    // dbg!("groups: {:?}", &groups[0]);
}

type ParseErr = String;

fn read_file(file_name: &str) -> IOResult<String> {
    let mut f = File::open(file_name)?;
    let mut content = String::new();
    f.read_to_string(&mut content)?;
    Ok(content)
}

fn parse_student_data(s: String) -> Result<Vec<Student>, ParseErr> {
    // split
    // let _s = "Spencer 14y C# 學生 [10 11 15 18 19]";

    let lines: Vec<_> = s.lines().collect();
    let mut students: Vec<Student> = Vec::with_capacity(lines.len());
    for _s in lines {
        // Find the indices of the square brackets
        let start_bracket = _s.find('[').unwrap();
        let end_bracket = _s.find(']').unwrap();

        // Extract the part inside the brackets and format it
        let numbers = &_s[start_bracket..=end_bracket];
        let formatted_numbers = numbers.replace(" ", ",");

        // Split the rest of the string by whitespace and collect into a vector
        let mut tokens: Vec<&str> = _s[..start_bracket].split_whitespace().collect();

        // Append the formatted number string
        tokens.push(&formatted_numbers);

        // let tokens: Vec<&str> = _s.split("[").collect();
        if tokens.len() != 5 {
            return Err(format!("wrong number of arguments in {:?}", tokens));
        }

        students.push(Student::new(
            tokens[0],
            tokens[1].trim_matches(|c| c == 'y').parse::<i32>().unwrap(),
            tokens[2],
            tokens[3],
            // trim [ and ]
            tokens[4]
                .to_string()
                .trim_matches(|c| c == '[' || c == ']')
                .split(",")
                .map(|num_str| num_str.trim().parse::<i8>())
                .collect::<Result<Vec<i8>, _>>()
                .unwrap(),
        ));
    }
    Ok(students)
}

// fn parse_string_vec<T: neon>(s: &str) -> Vec<i32> {}
