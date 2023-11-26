mod game;
mod hero;

use simple_logger::SimpleLogger;

fn main() {
    SimpleLogger::new().init().unwrap();
    let h0 = hero::Hero::new("Arthur".to_string(), hero::AttackType::Fireball);
    let h1 = hero::Hero::new("Betty".to_string(), hero::AttackType::Waterball);
    let mut game = game::Game::new(h0, h1);
    game.start();

    println!("Hello, world!");
}
