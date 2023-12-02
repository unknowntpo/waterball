mod group;
mod student;

use student::Student;

fn main() {
    let students = parse_student_data("student.data").unwrap();

    // first cut
    let groupStrategy = group::LangBasedGroupStrategy::new();

    groupStrategy.group(students).unwrap(); // can't call
}

type ParseErr = String;

fn parse_student_data(s: &str) -> Result<Vec<Student>, ParseErr> {
    // split
    let _s = "Spencer 14y C# 學生 [10 11 15 18 19]";

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

    println!("tokens: {:?}", tokens);

    Ok(vec![Student::new(
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
    )])
}

// fn parse_string_vec<T: neon>(s: &str) -> Vec<i32> {}
