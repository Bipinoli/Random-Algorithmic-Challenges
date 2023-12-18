use std::io;
use std::collections::HashMap;


fn main() {
    let mut line = String::new();
    let _ = {
        let _ = io::stdin().read_line(&mut line);
        line.trim().parse::<i32>().expect("please provide a valid int")
    };

    let lst = {
        line.clear();
        let _ = io::stdin().read_line(&mut line);
        let lst:Vec<i32> = line.trim().split(' ').map(|v| v.parse().unwrap()).collect();
        lst
    };


    let mut index = HashMap::<i32,bool>::new();
    for a in lst {
        if !index.contains_key(&a) {
            index.insert(a, true);
        }
    }

    println!("{l}", l = index.len());
}