import random

def is_prime(n):
    """Sprawdza czy liczba jest liczbą pierwszą."""
    if n <= 1:
        return False
    if n <= 3:
        return True
    if n % 2 == 0 or n % 3 == 0:
        return False
    i = 5
    while i * i <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return False
        i += 6
    return True

def generate_prime():
    """Generuje liczbę pierwszą o czterech cyfrach."""
    while True:
        prime_candidate = random.randint(1000, 9999)
        if is_prime(prime_candidate):
            return prime_candidate

def gcd(a, b):
    """Oblicza największy wspólny dzielnik dwóch liczb."""
    while b != 0:
        a, b = b, a % b
    return a

def mod_inverse(a, m):
    """Oblicza odwrotność modulo."""
    m0, x0, x1 = m, 0, 1
    while a > 1:
        q = a // m
        m, a = a % m, m
        x0, x1 = x1 - q * x0, x0
    return x1 + m0 if x1 < 0 else x1

def generate_keypair(p, q):
    """Generuje klucz prywatny i publiczny."""
    n = p * q
    phi = (p - 1) * (q - 1)
    while True:
        e = random.randint(2, phi - 1)
        if gcd(e, phi) == 1:
            break
    d = mod_inverse(e, phi)
    return ((e, n), (d, n))

def encrypt(message, public_key):
    """Szyfruje wiadomość."""
    e, n = public_key
    return [pow(ord(char), e, n) for char in message]

def decrypt(encrypted_message, private_key):
    """Odszyfrowuje wiadomość."""
    d, n = private_key
    return ''.join([chr(pow(char, d, n)) for char in encrypted_message])

# Krok 1
p = generate_prime()
q = generate_prime()

# Krok 2
public_key, private_key = generate_keypair(p, q)

# Krok 3
message = "Lorem ipsum dolor sit amet, consectetur adipiscing"

# Krok 4
encrypted_message = encrypt(message, public_key)

# Krok 5
decrypted_message = decrypt(encrypted_message, private_key)

# Krok 6
print("Oryginalna wiadomość:", message)
print("Zaszyfrowana wiadomość:", encrypted_message)
print("Odszyfrowana wiadomość:", decrypted_message)

# Krok 7
print("Wiadomość została zaszyfrowana kluczem publicznym.")
