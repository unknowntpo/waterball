mod game;
mod hero;

use simple_logger::SimpleLogger;
use std::sync::Arc;

fn main() {
    SimpleLogger::new().init().unwrap();
    let h0 = hero::Hero::new("Arthur".to_string(), Arc::new(hero::Fireball {}));
    let h1 = hero::Hero::new("Betty".to_string(), Arc::new(hero::Waterball {}));
    let mut game = game::Game::new(h0, h1);
    game.start();

    println!("Hello, world!");
}
