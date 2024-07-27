type SpriteId = i32;

pub trait Sprite {
    fn get_id(&self) -> SpriteId;
    fn get_type(&self) -> SpriteType;
    fn register_handler(&self) -> Box<dyn Handler>;
}

enum SpriteType {}

pub struct World {
    sprites: Vec<Box<dyn Sprite>>,
    axis: Vec<Vec<SpriteId>>,
    handlers: Vec<Box<dyn Handler>>,
}

trait Handler {
    fn handle(&self, base: i32, target: i32) -> bool;
}

impl World {
    pub fn new(sprites: Vec<Box<dyn Sprite>>, axis: Vec<Vec<SpriteId>>) -> Self {
        let handlers = sprites
            .iter()
            .map(|sprite| sprite.register_handler())
            .collect();

        Self {
            sprites,
            axis,
            handlers,
        }
    }

    pub fn run(&self) {
        loop {
            let (base, target) = self.get_input();
            self.move_to(base, target);
        }
    }

    fn get_input(&self) -> (i32, i32) {
        return (1, 2);
    }

    fn move_to(&self, base: i32, target: i32) {
        for handler in &self.handlers {
            if handler.handle(base, target) {
                break;
            }
        }

        todo!()
    }
}
