use crate::hero::Hero;
use log::info;

#[derive(Debug)]
pub struct Game {
    heroes: Vec<Hero>,
}

impl Game {
    pub fn new(heroes: Vec<Hero>) -> Game {
        Game { heroes: heroes }
    }
    pub fn start(&self) {
        log::info!("starting game, heroes: {:?}", self.heroes);
        // todo!("add impl");
    }
}
