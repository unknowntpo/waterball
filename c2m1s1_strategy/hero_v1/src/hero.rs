use log::info;

#[derive(Debug)]
pub struct Hero {
    pub name: String,
    hp: i32,
    attack_type: AttackType,
}

#[derive(Debug)]
pub enum AttackType {
    Fireball,
    Waterball,
    Earthball,
}

impl Hero {
    pub fn new(name: String, attack_type: AttackType) -> Hero {
        Hero {
            name: name,
            attack_type: attack_type,
            hp: 500,
        }
    }
    pub fn attack(&self, hero: &mut Hero) {
        info!("hero {:?} attack hero: {:?}", self, hero);
        match self.attack_type {
            AttackType::Fireball => {
                hero.damage((self.hp as f64 * 0.5) as i32);
            }
            AttackType::Waterball => {
                for _ in 0..3 {
                    hero.damage(50);
                }
            }
            AttackType::Earthball => {
                for _ in 0..10 {
                    hero.damage(20);
                }
            }
        }
    }
    fn damage(&mut self, hp: i32) {
        info!("causing {} damage on {:?}", hp, self);
        self.hp -= hp;
    }
    pub fn is_dead(&self) -> bool {
        return self.hp <= 0;
    }
}
