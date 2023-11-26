#[derive(Debug)]
pub struct Hero {
    name: String,
    hp: i32,
    attack_type: String,
}

impl Hero {
    pub fn new(name: String, attack_type: String) -> Hero {
        Hero {
            name: name,
            attack_type: attack_type,
            hp: 500,
        }
    }
    pub fn attack(&self, hero: Hero) {
        todo!("implement attack based in self.attack_type");
    }
    pub fn is_dead(&self) -> bool {
        return self.hp <= 0;
    }
}
