LiveOSC2 {
  classvar self;
  classvar sndPort=9001;
  classvar recvPort=9000;
  classvar livePath="/live/%";
  var lo;

  *new {
    if(self.isNil){
      self = super.new.init;
    }
    ^self;
  }

  init {
    lo = NetAddr("127.0.0.1", sndPort);
  }

  prSend { |path, message|
    lo.sendMsg(livePath.format(path), *message);
  }

  *prClose{
    self = nil;
  }

  shutdown {
    LiveOSC2.prClose;
  }

  // SET
  oscCallback { |path, cb|
    if (cb.isFunction.not) { cb = {|msg, time, addr, rcv| [msg, time, addr, rcv].postln }; }
    ^OSCFunc(cb, livePath.format(path), NetAddr("127.0.0.1"), recvPort: recvPort);
  }


  // Song
  selection { |track_id, scene_id, width, height|
    this.prSend("selection", [track_id, scene_id, width, height]);
  }

  // Transport
  tempo { |tempo|
    this.prSend("tempo", [tempo.asFloat]);
  }

  time {
    this.prSend("time");
  }

  cue { |beat_time, name|
    this.prSend("cue", [beat_time, name]);
  }

  cueJump { |name|
    this.prSend("cue/jump", [name]);
  }

  cueNext {
    this.prSend("cue/next");
  }

  cuePrev {
    this.prSend("cue/prev");
  }

  play {
    this.prSend("play");
  }

  play_continue {
    this.prSend("play/continue");
  }

  play_select {
    this.prSend("play/select");
  }

  jump { |beats|
    this.prSend("jump", [beats]);
  }

  undo {
    this.prSend("undo");
  }

  redo {
    this.prSend("redo");
  }

  overdub {
    this.prSend("overdub");
  }

  metronome {
    this.prSend("metronome");
  }

  loop {
    this.prSend("loop");
  }

  signature {
    this.prSend("signature");
  }

  // Scenes
  scene {
    this.prSend("scenes");
  }

  sceneName { |scene_id, name|
    this.prSend("scenes/name", [scene_id, name]);
  }

  sceneBlock { |scene_id, height|
    this.prSend("scene/name/block", [scene_id, height]);
  }

  sceneColor { |scene_id, color|
    this.prSend("scene/color", [scene_id, color]);
  }

  sceneState { |scene_id|
    this.prSend("scene/state", [scene_id]);
  }

  sceneSelect { |scene_id|
    this.prSend("scene/select", [scene_id]);
  }

  // Tracks
  tracks {
    this.prSend("tracks");
  }

  trackPrSend { |action, args|
    this.prSend("track/%".format(action.asString), args);
  }

  trackArm { |track_id, state|
    this.trackPrSend("arm", [track_id, state]);
  }

  trackMute { |track_id, state|
    this.trackPrSend("mute", [track_id, state]);
  }

  trackSolo { |track_id, state|
    this.trackPrSend("solo", [track_id, state]);
  }

  trackVolume { |track_id, volume|
    this.trackPrSend("volume", [track_id, volume.asFloat]);
  }

  trackPanning { |track_id, panning|
    this.trackPrSend("volume", [track_id, panning.asFloat]);
  }

  trackSend { |track_id, send_id, value|
    this.trackPrSend("send", [track_id, send_id, value.asFloat]);
  }

  trackSelect { |track_id|
    this.trackPrSend("select", [track_id]);
  }

  trackCrossfader { |track_id, state|
    this.trackPrSend("crossfader", [track_id, state]);
  }

  trackName { |track_id, name|
    this.trackPrSend("name", [track_id, name]);
  }

  trackColor { |track_id, color|
    this.trackPrSend("color", [track_id, color]);
  }

  trackStop { |track_id, state|
    this.trackPrSend("stop", [track_id, state]);
  }

  trackState { |track_id, state|
    this.trackPrSend("state", [track_id, state]);
  }

  trackDevices { |track_id, device_id|
    this.trackPrSend("devices", [track_id, device_id]);
  }

  trackDeviceRange { |track_id, device_id, min, max|
    this.trackPrSend("device/range", [track_id, device_id, min, max]);
  }

  trackDeviceParam { |track_id, device_id, value|
    this.trackPrSend("device/param", [track_id, device_id, value.asFloat]);
  }

  trackDeviceSelect { |track_id, device_id|
    this.trackPrSend("device/select", [track_id, device_id]);
  }

  trackMonitoring { |track_id, state|
    this.trackPrSend("monitoring", [track_id, state]);
  }

  // Return
  returnPrSend { |action, args|
    this.prSend("return/%".format(action.asString), args);
  }

  returnArm { |return_id, state|
    this.returnPrSend("arm", [return_id, state]);
  }

  returnMute { |return_id, state|
    this.returnPrSend("mute", [return_id, state]);
  }

  returnSolo { |return_id, state|
    this.returnPrSend("solo", [return_id, state]);
  }

  returnVolume { |return_id, volume|
    this.returnPrSend("volume", [return_id, volume.asFloat]);
  }

  returnPanning { |return_id, panning|
    this.returnPrSend("volume", [return_id, panning.asFloat]);
  }

  returnSend { |return_id, send_id, value|
    this.returnPrSend("send", [return_id, send_id, value.asFloat]);
  }

  returnSelect { |return_id|
    this.returnPrSend("select", [return_id]);
  }

  returnCrossfader { |return_id, state|
    this.returnPrSend("crossfader", [return_id, state]);
  }

  returnName { |return_id, name|
    this.returnPrSend("name", [return_id, name]);
  }

  returnColor { |return_id, color|
    this.returnPrSend("color", [return_id, color]);
  }

  returnStop { |return_id, state|
    this.returnPrSend("stop", [return_id, state]);
  }

  returnState { |return_id, state|
    this.returnPrSend("state", [return_id, state]);
  }

  returnDevices { |return_id, device_id|
    this.returnPrSend("devices", [return_id, device_id]);
  }

  returnDeviceRange { |return_id, device_id, min, max|
    this.returnPrSend("device/range", [return_id, device_id, min, max]);
  }

  returnDeviceParam { |return_id, device_id, value|
    this.returnPrSend("device/param", [return_id, device_id, value.asFloat]);
  }

  returnDeviceSelect { |return_id, device_id|
    this.returnPrSend("device/select", [return_id, device_id]);
  }

  // Master
  masterPrSend { |action, args|
    this.prSend("master/%".format(action.asString), args);
  }

  masterArm { |state|
    this.masterPrSend("arm", [state]);
  }

  masterMute { |state|
    this.masterPrSend("mute", [state]);
  }

  masterSolo { |state|
    this.masterPrSend("solo", [state]);
  }

  masterVolume { |volume|
    this.masterPrSend("volume", [volume.asFloat]);
  }

  masterPanning { |panning|
    this.masterPrSend("volume", [panning.asFloat]);
  }

  masterSend { |send_id, value|
    this.masterPrSend("send", [send_id, value.asFloat]);
  }

  masterSelect {
    this.masterPrSend("select");
  }

  masterCrossfader { |state|
    this.masterPrSend("crossfader", [state]);
  }

  masterDevices { |device_id|
    this.masterPrSend("devices", [device_id]);
  }

  masterDeviceRange { |device_id, min, max|
    this.masterPrSend("device/range", [device_id, min, max]);
  }

  masterDeviceParam { |device_id, value|
    this.masterPrSend("device/param", [device_id, value.asFloat]);
  }

  masterDeviceSelect { |device_id|
    this.masterPrSend("device/select", [device_id]);
  }

  // Clips
  clipPrSend { |action, args|
    this.prSend("clip/%".format(action.asString), args);
  }

  clipState { |track_id, scene_id|
    this.clipPrSend("state", [track_id, scene_id]);
  }

  clipStateBlock { |track_id, scene_id, width, height|
    this.clipPrSend("state/block", [track_id, scene_id, width, height]);
  }

  clipPlay { |track_id, scene_id|
    this.clipPrSend("play", [track_id, scene_id]);
  }

  clipStop { |track_id, scene_id|
    this.clipPrSend("stop", [track_id, scene_id]);
  }

  clipView { |track_id, scene_id|
    this.clipPrSend("view", [track_id, scene_id]);
  }

  clipName { |track_id, scene_id|
    this.clipPrSend("name", [track_id, scene_id]);
  }

  clipNameBlock { |track_id, scene_id, height, width|
    this.clipPrSend("name/block", [track_id, scene_id, height, width]);
  }

  clipColor { |track_id, scene_id|
    this.clipPrSend("color", [track_id, scene_id]);
  }

  clipMuted { |track_id, scene_id, muted|
    this.clipPrSend("muted", [track_id, scene_id, muted]);
  }

  clipNotes { |track_id, scene_id, start_time, start_pitch, time_length, pitch_length|
    this.clipPrSend("notes", [track_id, scene_id, start_time.asFloat, start_pitch, time_length.asFloat, pitch_length]);
  }

  clipNotesAdd { |track_id, scene_id, pitch, time, duration, velocity, mute|
    /*
    pitch: 0 to 127
    time (in beats): 0=beat 1, 1=beat 2, 0.5 in between the two
    duration (in beats): 0.25 quarter beat, 0.5 half beat, 1 beat
    velocity: 0 to 127
    mute: 0 or 1
    */
    this.clipPrSend("notes/add", [track_id, scene_id, pitch, time.asFloat, duration.asFloat, velocity, mute]);
  }

  clipNotesRemove { |track_id, scene_id, start_time, start_pitch, time_length, pitch_length|
    this.clipPrSend("notes/remove", [track_id, scene_id, start_time.asFloat, start_pitch, time_length.asFloat, pitch_length]);
  }

  clipLooping { |track_id, scene_id|
    this.clipPrSend("looping", [track_id, scene_id]);
  }

  clipLoopStart { |track_id, scene_id|
    this.clipPrSend("loopstart", [track_id, scene_id]);
  }

  clipLoopEnd { |track_id, scene_id|
    this.clipPrSend("loopend", [track_id, scene_id]);
  }

  clipLoopJump { |track_id, scene_id|
    this.clipPrSend("loopjump", [track_id, scene_id]);
  }

  clipStart { |track_id, scene_id|
    this.clipPrSend("start", [track_id, scene_id]);
  }

  clipEnd { |track_id, scene_id|
    this.clipPrSend("end", [track_id, scene_id]);
  }

  clipWarping { |track_id, scene_id|
    this.clipPrSend("warping", [track_id, scene_id]);
  }

  clipPitch { |track_id, scene_id|
    this.clipPrSend("pitch", [track_id, scene_id]);
  }

  clipCreate { |track_id, scene_id|
    this.clipPrSend("create", [track_id, scene_id]);
  }

  clipDelete { |track_id, scene_id|
    this.clipPrSend("delete", [track_id, scene_id]);
  }

  // Browser
  browserList { |name|
    name = if (name.notNil, { [name] }, { "" });
    this.prSend("browser/list", name);
  }

  browserLoadPrSend { |path, name|
    this.prSend("browser/%/load".format(path), name)
  }

  browserDrumsLoad { |name|
    this.browserLoadPrSend("drums", [name]);
  }

  browserInstrumentsLoad { |name|
    this.browserLoadPrSend("instruments", [name]);
  }

  browserAudiofxLoad { |name|
    this.browserLoadPrSend("audiofx", [name]);
  }

  browserMidifxLoad { |name|
    this.browserLoadPrSend("midifx", [name]);
  }

  browserM4lLoad { |name|
    this.browserLoadPrSend("m4l", [name]);
  }

  browserPluginsLoad { |name|
    this.browserLoadPrSend("plugins", [name]);
  }

  browserClipsLoad { |name|
    this.browserLoadPrSend("clips", [name]);
  }

  browserSamplesLoad { |name|
    this.browserLoadPrSend("samples", [name]);
  }

  browserPacksLoad { |name|
    this.browserLoadPrSend("packs", [name]);
  }

  browserUserlibLoad { |name|
    this.browserLoadPrSend("userlib", [name]);
  }

  browserCurrentprjLoad { |name|
    this.browserLoadPrSend("currentprj", [name]);
  }

  browserUserfoldersLoad { |name|
    this.browserLoadPrSend("userfolders", [name]);
  }


}