use crate::student::*;

pub struct Group {
    number: i32,
    students: Vec<Student>,
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
    pub fn group(&self, students: Vec<Student>) -> Result<Vec<Group>, GroupErr> {
        todo!("impl LangBasedGroupStrategy");
    }
}

// impl GroupStrategy for LangBasedGroupStrategy {
//     fn group(&self, students: Vec<Student>) -> Result<Vec<Group>, GroupErr> {
//         todo!("impl LangBasedGroupStrategy");
//     }
// }
