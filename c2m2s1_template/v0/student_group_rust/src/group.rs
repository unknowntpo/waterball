use crate::student::*;
use lazy_static::lazy_static;
use std::sync::Mutex;

use std::collections::HashMap;

lazy_static! {
    static ref GROUP_ID_COUNTER: Mutex<i32> = Mutex::new(0);
}

#[derive(Debug)]
pub struct Group {
    number: i32,
    students: Vec<Student>,
}

impl Group {
    pub fn new(students: &[Student]) -> Group {
        let mut id = GROUP_ID_COUNTER.lock().unwrap();
        *id += 1;

        Group {
            number: *id,
            students: students.to_vec(),
        }
    }
}

type GroupErr = String;

pub trait GroupStrategy {
    fn group(&self, students: Vec<Student>) -> Result<Vec<Group>, GroupErr>;
}

pub struct LangBasedGroupStrategy {}

impl LangBasedGroupStrategy {
    pub fn new() -> LangBasedGroupStrategy {
        LangBasedGroupStrategy {}
    }
    // Spencer 14y C# 學生 [10 11 15 18 19]
    // first cut: by language
    // second cut: 6 people a group
    // third cut: merge group, <6 merge with >=6 and with same language
    pub fn group(&self, students: Vec<Student>) -> Result<Vec<Group>, GroupErr> {
        let mut groups: HashMap<String, Group> = HashMap::new();

        // first cut: by language
        for student in students {
            groups
                .entry(student.language.clone())
                .or_insert(Group::new(&[student.clone()]))
                .students
                .push(student.clone());
        }

        let groups_by_lang: Vec<Group> = groups.into_values().collect();

        let mut groups_by_num_students: Vec<Group> = Vec::new();
        // second cut: 6 people a group
        for mut group in groups_by_lang {
            let right_students = group.students.split_off(6);
            let right_group = Group::new(&right_students);
            groups_by_num_students.push(group);
            groups_by_num_students.push(right_group);
            // test move
        }

        // third cut: merge group, <6 merge with >=6 and with same language
        Ok(groups_by_num_students)
    }
}

// impl GroupStrategy for LangBasedGroupStrategy {
//     fn group(&self, students: Vec<Student>) -> Result<Vec<Group>, GroupErr> {
//         todo!("impl LangBasedGroupStrategy");
//     }
// }
