let a = 1
var b = 2.2e5
var break = false

while (b > a && !break) {
	b = b - a
	if (b <= 0 && a <= 0) {
		break;
	}
	a = a / 15.0
}