use crate::hero::Hero;
use log::info;
use std::mem;

#[derive(Debug)]
pub struct Game {
    h0: Hero,
    h1: Hero,
}

impl Game {
    pub fn new(h0: Hero, h1: Hero) -> Game {
        Game { h0: h0, h1: h1 }
    }
    pub fn start(&mut self) {
        log::info!("starting game, heroes: {:?} {:?}", self.h0, self.h1);
        self.nextTurn();
    }

    fn nextTurn(&mut self) {
        let attacker = &mut self.h0;
        let attacked = &mut self.h1;
        attacker.attack(attacked);
        if attacked.is_dead() {
            log::info!("Hero [{}] is dead! {} winned", attacked.name, attacker.name);
        } else {
            mem::swap(attacker, attacked);
            self.nextTurn();
        }
    }

    // private void nextTurn() {
    // 	Hero attacker = heroes.get(0);
    // 	Hero attacked = heroes.get(1);
    // 	attacker.attack(attacked);
    // 	swap(heroes, 0, 1);
    // 	if (attacked.isDead()) {
    // 		printf("英雄 %s 死亡！%s 獲勝！\n", attacked.getName(), attacker.getName());
    // 	} else {
    // 		nextTurn();
    // 	}
    // }
}
