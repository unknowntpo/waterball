mod game;
mod hero;

use simple_logger::SimpleLogger;

fn main() {
    SimpleLogger::new().init().unwrap();
    let h0 = hero::Hero::new("Arthur".to_string(), "Waterball".to_string());
    let h1 = hero::Hero::new("Betty".to_string(), "Fireball".to_string());
    let h2 = hero::Hero::new("Kelly".to_string(), "Earthball".to_string());
    let game = game::Game::new(vec![h0, h1, h2]);
    game.start();

    println!("Hello, world!");
}
