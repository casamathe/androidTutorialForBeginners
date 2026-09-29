import {interpolate, useCurrentFrame} from 'remotion';

// Chien dessiné en SVG. `mood` 0 = normal, 1 = langue dehors / yeux fous.
export const Dog = ({mood = 0, look = 0}: {mood?: number; look?: number}) => {
  const f = useCurrentFrame();
  const earFlap = Math.sin(f / 3) * 8 * (0.4 + mood);
  const tail = Math.sin(f / 2) * 25;
  const tongue = 14 + mood * 40 + Math.sin(f / 2.5) * 6 * mood;
  const pupilX = look * 8 + Math.sin(f / 4) * 6 * mood;
  const pupilY = Math.cos(f / 5) * 5 * mood;
  const blink = f % 90 > 84 ? 0.1 : 1;
  return (
    <svg width={420} height={460} viewBox="-210 -230 420 460">
      <g transform={`rotate(${tail} 150 90)`}>
        <ellipse cx={175} cy={70} rx={16} ry={55} fill="#a86a3c" transform="rotate(30 175 70)" />
      </g>
      <ellipse cx={0} cy={150} rx={120} ry={80} fill="#c98a52" />
      <ellipse cx={-55} cy={215} rx={32} ry={18} fill="#a86a3c" />
      <ellipse cx={55} cy={215} rx={32} ry={18} fill="#a86a3c" />
      <g transform={`rotate(${-20 - earFlap} -110 -90)`}>
        <ellipse cx={-125} cy={-40} rx={38} ry={85} fill="#7a4a26" />
      </g>
      <g transform={`rotate(${20 + earFlap} 110 -90)`}>
        <ellipse cx={125} cy={-40} rx={38} ry={85} fill="#7a4a26" />
      </g>
      <circle cx={0} cy={-50} r={115} fill="#d99a62" />
      <ellipse cx={0} cy={-5} rx={62} ry={48} fill="#f4dcc0" />
      {[-42, 42].map((x) => (
        <g key={x}>
          <circle cx={x} cy={-75} r={30} fill="#fff" stroke="#333" strokeWidth={3} />
          <g transform={`translate(${x + pupilX} ${-75 + pupilY}) scale(1 ${blink})`}>
            <circle r={12 + mood * 4} fill="#222" />
            <circle cx={-4} cy={-4} r={4} fill="#fff" />
          </g>
        </g>
      ))}
      <ellipse cx={0} cy={-22} rx={22} ry={15} fill="#222" />
      <ellipse cx={-6} cy={-27} rx={6} ry={3} fill="#666" />
      <path d="M0 -8 V12" stroke="#222" strokeWidth={4} />
      <path d="M-30 14 Q0 40 30 14" fill="none" stroke="#222" strokeWidth={4} strokeLinecap="round" />
      <path
        d={`M-14 26 Q-14 ${26 + tongue} 0 ${26 + tongue} Q14 ${26 + tongue} 14 26 Z`}
        fill="#ff6f91"
        stroke="#c2345a"
        strokeWidth={3}
      />
    </svg>
  );
};
