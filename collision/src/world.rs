type SpriteId = i32;

pub trait Sprite {
    fn getId() -> SpriteId;
    fn getType() -> SpriteType;
    fn register_handler(&self) -> dyn Handler;
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
    pub fn new(sprites: Vec<Sprite>, axis: Vec<Vec<SpriteId>>) -> Self {
        Self { sprites, axis }
    }

    pub fn run(&self) {
        loop {
            let (base, target) = self.getInput();
            self.moveTo(base, target);
        }
    }

    fn getInput(&self) -> (i32, i32) {
        return (1, 2);
    }

    fn moveTo(&self, base: i32, target: i32) {
        for handler in self.handlers {
            if handler.handle(base, target) {
                break;
            }
        }

        todo!()
    }
}
