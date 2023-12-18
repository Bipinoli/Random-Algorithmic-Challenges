use std::io;

fn read_line_as_vec() -> Vec<i32> {
    let mut line = String::new();
    let _ = io::stdin().read_line(&mut line).unwrap();
    line.trim().split(' ').map(|i| i.parse().unwrap()).collect()
}

fn read_line_as_desc_vec() -> Vec<i32> {
    let mut v = read_line_as_vec();
    v.sort_by(|a, b| b.cmp(a));
    v
}

fn main() {
    let k = {
        let v = read_line_as_vec();
        v[2]
    };

    let users = read_line_as_desc_vec();
    let rooms = read_line_as_desc_vec();

    let mut ri = 0;
    let mut ans = 0;

    for i in 0..users.len() {
        while ri < rooms.len() && users[i] + k < rooms[ri] {
            ri += 1;
        }
        if ri == rooms.len() {
            break;
        }
        if (rooms[ri] - users[i]).abs() <= k {
            ans += 1;
            ri += 1;
        }
    }

    println!("{ans}");
}