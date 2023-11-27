use log::info;
use std::fmt;
use std::sync::Arc;

pub struct Hero {
    pub name: String,
    hp: i32,
    attack_type: Arc<dyn AttackType>,
}

pub trait AttackType {
    fn attack(&self, attacker: &mut Hero, attacked: &mut Hero);
}

pub struct Fireball {}
impl AttackType for Fireball {
    fn attack(&self, attacker: &mut Hero, attacked: &mut Hero) {
        attacked.damage((attacker.hp as f64 * 0.5) as i32);
    }
}

pub struct Waterball {}
impl AttackType for Waterball {
    fn attack(&self, _attacker: &mut Hero, attacked: &mut Hero) {
        for _ in 0..3 {
            attacked.damage(50);
        }
    }
}

#[derive(Clone)]
pub struct Earthball {}
impl AttackType for Earthball {
    fn attack(&self, _attacker: &mut Hero, attacked: &mut Hero) {
        for _ in 0..10 {
            attacked.damage(20);
        }
    }
}

impl Hero {
    pub fn new(name: String, attack_type: Arc<dyn AttackType>) -> Hero {
        Hero {
            name: name,
            attack_type: attack_type,
            hp: 500,
        }
    }
    pub fn attack(&mut self, hero: &mut Hero) {
        info!("hero {:?} attack hero: {:?}", self, hero);
        let attack_type = &self.attack_type.clone();
        attack_type.attack(self, hero);
    }
    fn damage(&mut self, hp: i32) {
        info!("causing {} damage on {:?}", hp, self);
        self.hp -= hp;
    }
    pub fn is_dead(&self) -> bool {
        return self.hp <= 0;
    }
}

impl fmt::Debug for Hero {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        f.debug_struct("Hero")
            .field("name", &self.name)
            .field("hp", &self.hp)
            .field("attack_type", &"Custom Debug Implementation")
            .finish()
    }
}
