import {Composition} from 'remotion';
import {FunnyDog} from './FunnyDog';

export const Root = () => (
  <Composition
    id="FunnyDog"
    component={FunnyDog}
    durationInFrames={300}
    fps={30}
    width={1280}
    height={720}
  />
);
