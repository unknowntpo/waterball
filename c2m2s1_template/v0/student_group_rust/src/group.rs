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

    pub fn merge(&mut self, g0: &mut Group) {
        self.students.append(&mut g0.students);
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
}

impl GroupStrategy for LangBasedGroupStrategy {
    // Spencer 14y C# 學生 [10 11 15 18 19]
    // first cut: by language
    // second cut: 6 people a group
    // third cut: merge group, <6 merge with >=6 and with same language
    fn group(&self, students: Vec<Student>) -> Result<Vec<Group>, GroupErr> {
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
            while group.students.len() > 6 {
                let right_students: Vec<Student>;
                right_students = group.students.split_off(group.students.len() - 6);
                let right_group = Group::new(&right_students);
                groups_by_num_students.push(right_group);
            }
            groups_by_num_students.push(group);
        }

        // third cut: merge group, <6 merge with >=6 and with same language
        let mut non_full_groups: Vec<Group> = Vec::new();
        let mut full_groups: Vec<Group> = Vec::new();
        for group in groups_by_num_students {
            if group.students.len() == 6 {
                full_groups.push(group);
            } else {
                non_full_groups.push(group);
            }
        }

        for mut g0 in &mut non_full_groups {
            for g1 in &mut full_groups {
                if g1.students[0].language == g0.students[0].language {
                    g1.merge(&mut g0);
                    break;
                }
            }
        }

        for g in &non_full_groups {
            println!("non full groups len: {}", g.students.len());
        }

        let _ = &full_groups
            .iter()
            .take_while(|g| g.students.len() == 6)
            .map(|g| println!("len {} ", g.students.len()));

        Ok(full_groups)
    }
}

pub struct TimeSlotsBasedGroupStrategy {}

impl TimeSlotsBasedGroupStrategy {
    pub fn new() -> TimeSlotsBasedGroupStrategy {
        TimeSlotsBasedGroupStrategy {}
    }
}

impl GroupStrategy for TimeSlotsBasedGroupStrategy {
    // Spencer 14y C# 學生 [10 11 15 18 19]
    // first cut: by language
    // second cut: 6 people a group
    // third cut: merge group, <6 merge with >=6 and with same language
    fn group(&self, students: Vec<Student>) -> Result<Vec<Group>, GroupErr> {
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
            while group.students.len() > 6 {
                let right_students: Vec<Student>;
                right_students = group.students.split_off(group.students.len() - 6);
                let right_group = Group::new(&right_students);
                groups_by_num_students.push(right_group);
            }
            groups_by_num_students.push(group);
        }

        // third cut: merge group, <6 merge with >=6 and with same language
        let mut non_full_groups: Vec<Group> = Vec::new();
        let mut full_groups: Vec<Group> = Vec::new();
        for group in groups_by_num_students {
            if group.students.len() == 6 {
                full_groups.push(group);
            } else {
                non_full_groups.push(group);
            }
        }

        for mut g0 in &mut non_full_groups {
            for g1 in &mut full_groups {
                if g1.students[0].language == g0.students[0].language {
                    g1.merge(&mut g0);
                    break;
                }
            }
        }

        for g in &non_full_groups {
            println!("non full groups len: {}", g.students.len());
        }

        let _ = &full_groups
            .iter()
            .take_while(|g| g.students.len() == 6)
            .map(|g| println!("len {} ", g.students.len()));

        Ok(full_groups)
    }
}
