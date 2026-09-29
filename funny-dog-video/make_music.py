import math, random, struct, wave

SR = 44100
DUR = 10.0
N = int(SR * DUR)
buf = [0.0] * N
random.seed(1)

def add(t0, samples, vol=1.0):
    i0 = int(t0 * SR)
    for i, s in enumerate(samples):
        if i0 + i >= N:
            break
        buf[i0 + i] += s * vol

def tone(freq, dur, wave_type="square", decay=6.0):
    n = int(dur * SR)
    out = []
    for i in range(n):
        t = i / SR
        ph = (t * freq) % 1.0
        if wave_type == "square":
            v = 1.0 if ph < 0.5 else -1.0
        elif wave_type == "tri":
            v = 4 * abs(ph - 0.5) - 1
        else:
            v = math.sin(2 * math.pi * ph)
        env = math.exp(-decay * t) * min(1.0, t * 200)
        out.append(v * env)
    return out

def kick(dur=0.18):
    out = []
    for i in range(int(dur * SR)):
        t = i / SR
        f = 120 * math.exp(-25 * t) + 40
        out.append(math.sin(2 * math.pi * f * t) * math.exp(-14 * t))
    return out

def hat(dur=0.05):
    return [(random.random() * 2 - 1) * math.exp(-60 * i / SR) for i in range(int(dur * SR))]

def woof(dur=0.22):
    out = []
    for i in range(int(dur * SR)):
        t = i / SR
        f = 420 * math.exp(-5 * t) + 120
        v = math.sin(2 * math.pi * f * t) + 0.4 * math.sin(2 * math.pi * 2 * f * t)
        v += (random.random() * 2 - 1) * 0.25
        out.append(v * math.exp(-9 * t) * min(1.0, t * 300))
    return out

def note(name):
    names = {"C": 0, "D": 2, "E": 4, "F": 5, "G": 7, "A": 9, "B": 11}
    n = names[name[0]] + 12 * (int(name[-1]) - 4)
    return 261.63 * 2 ** (n / 12)

BPM = 150
beat = 60 / BPM
# 2 mesures de 4 temps ~ 3.2 s ; on boucle pour couvrir 10 s
melody = ["E5", "G5", "E5", "C5", "D5", "F5", "D5", "G4",
          "E5", "G5", "C6", "G5", "F5", "E5", "D5", "C5"]
bass = ["C3", "C3", "G3", "G3", "F3", "F3", "G3", "G3"]

t = 0.0
step = 0
while t < DUR:
    m = melody[step % len(melody)]
    add(t, tone(note(m), beat * 0.9, "square", 7), 0.16)
    if step % 2 == 0:
        b = bass[(step // 2) % len(bass)]
        add(t, tone(note(b), beat * 1.8, "tri", 3), 0.45)
        add(t, kick(), 0.7)
    add(t + beat / 2, hat(), 0.12)
    if step % 4 == 2:
        add(t, hat(0.12), 0.18)
    t += beat
    step += 1

# aboiements calés sur les scènes
for ts in [0.9, 1.1, 2.9, 3.3, 3.7, 8.7]:
    add(ts, woof(), 0.55)

# fade out final
for i in range(N):
    tt = i / SR
    if tt > DUR - 0.6:
        buf[i] *= max(0.0, (DUR - tt) / 0.6)

peak = max(abs(x) for x in buf) or 1.0
with wave.open("public/music.wav", "wb") as w:
    w.setnchannels(1)
    w.setsampwidth(2)
    w.setframerate(SR)
    w.writeframes(b"".join(struct.pack("<h", int(x / peak * 0.9 * 32767)) for x in buf))
