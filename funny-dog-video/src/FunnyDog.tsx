import {AbsoluteFill, Sequence, interpolate, spring, useCurrentFrame, useVideoConfig} from 'remotion';
import {Dog} from './Dog';

const Caption = ({text, color = '#fff'}: {text: string; color?: string}) => {
  const f = useCurrentFrame();
  const {fps} = useVideoConfig();
  const s = spring({frame: f, fps, config: {damping: 8}});
  return (
    <div
      style={{
        position: 'absolute',
        top: 50,
        width: '100%',
        textAlign: 'center',
        fontFamily: 'Comic Sans MS, Impact, sans-serif',
        fontSize: 72,
        fontWeight: 900,
        color,
        textShadow: '4px 4px 0 #000, -2px -2px 0 #000',
        transform: `scale(${s}) rotate(${Math.sin(f / 5) * 2}deg)`,
      }}
    >
      {text}
    </div>
  );
};

const Emoji = ({e, x, delay}: {e: string; x: number; delay: number}) => {
  const f = useCurrentFrame() - delay;
  if (f < 0) return null;
  const y = interpolate(f, [0, 60], [700, -100], {extrapolateRight: 'clamp'});
  return <div style={{position: 'absolute', left: x, top: y, fontSize: 80, transform: `rotate(${f * 6}deg)`}}>{e}</div>;
};

// Scène 1 : le chien entre en glissant, l'air sérieux
const Intro = () => {
  const f = useCurrentFrame();
  const x = interpolate(f, [0, 30], [-700, 0], {extrapolateRight: 'clamp'});
  return (
    <AbsoluteFill style={{background: '#8fd3f4', alignItems: 'center', justifyContent: 'center'}}>
      <Caption text="Voici Bobby 🐶" />
      <div style={{transform: `translateX(${x}px)`}}><Dog mood={0} look={1} /></div>
    </AbsoluteFill>
  );
};

// Scène 2 : il voit un écureuil, il devient fou
const Squirrel = () => {
  const f = useCurrentFrame();
  const sx = interpolate(f, [0, 90], [1400, -200]);
  const shake = Math.sin(f * 1.5) * 10;
  return (
    <AbsoluteFill style={{background: '#b8e986'}}>
      <Caption text="ÉCUREUIL !!! 🐿️" color="#ffe14d" />
      <AbsoluteFill style={{alignItems: 'center', justifyContent: 'center', transform: `translateX(${shake}px)`}}>
        <Dog mood={1} look={interpolate(f, [0, 90], [1, -1])} />
      </AbsoluteFill>
      <div style={{position: 'absolute', left: sx, top: 520, fontSize: 110}}>🐿️</div>
    </AbsoluteFill>
  );
};

// Scène 3 : il court en cercle, attrape sa queue, danse
const Zoomies = () => {
  const f = useCurrentFrame();
  const angle = f / 8;
  const dx = Math.cos(angle) * 350;
  const dy = Math.sin(angle) * 60;
  const rot = Math.sin(f / 4) * 15;
  return (
    <AbsoluteFill style={{background: '#ffb6c1', alignItems: 'center', justifyContent: 'center'}}>
      <Caption text="ZOOMIES ! 💨" color="#fff" />
      {['🦴', '🎾', '🦴', '🎾', '⭐'].map((e, i) => (
        <Emoji key={i} e={e} x={150 + i * 240} delay={i * 12} />
      ))}
      <div style={{transform: `translate(${dx}px, ${dy}px) rotate(${rot}deg) scale(${0.8 + Math.sin(f / 6) * 0.1})`}}>
        <Dog mood={1} look={Math.cos(angle)} />
      </div>
    </AbsoluteFill>
  );
};

// Scène 4 : fin, il s'endort
const Ending = () => {
  const f = useCurrentFrame();
  const pop = spring({frame: f, fps: 30, config: {damping: 6}});
  return (
    <AbsoluteFill style={{background: '#1a1a3a', alignItems: 'center', justifyContent: 'center'}}>
      <Caption text="Bon chien ! 😴" color="#ffd166" />
      <div style={{transform: `scale(${pop}) rotate(${Math.sin(f / 15) * 4}deg)`}}>
        <Dog mood={0.3} />
      </div>
      {['Z', 'z', 'Z'].map((z, i) => (
        <div
          key={i}
          style={{
            position: 'absolute',
            left: 830 + i * 50,
            top: 250 - ((f + i * 20) % 60) * 2 - i * 40,
            fontSize: 60 + i * 20,
            fontWeight: 900,
            color: '#fff',
            opacity: 0.8,
          }}
        >
          {z}
        </div>
      ))}
    </AbsoluteFill>
  );
};

export const FunnyDog = () => (
  <AbsoluteFill>
    <Sequence from={0} durationInFrames={75}><Intro /></Sequence>
    <Sequence from={75} durationInFrames={90}><Squirrel /></Sequence>
    <Sequence from={165} durationInFrames={90}><Zoomies /></Sequence>
    <Sequence from={255} durationInFrames={45}><Ending /></Sequence>
  </AbsoluteFill>
);
