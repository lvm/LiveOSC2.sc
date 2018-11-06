LiveOSC2 {
  classvar self;
  var lo, <>port=9001;

  *new {
    if(self.isNil){
      self = super.new.init;
    }
    ^self;
  }

  init {
    lo = NetAddr("127.0.0.1", port);
  }

  prSend { |path, message|
    lo.sendMsg("/live/%".format(path), *message);
  }

  *prClose{
    self = nil;
  }

  shutdown {
    LiveOSC2.prClose;
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

  cue_jump { |name|
    this.prSend("cue/jump", [name]);
  }

  cue_next {
    this.prSend("cue/next");
  }

  cue_prev {
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

  scene_name { |scene_id, name|
    this.prSend("scenes/name", [scene_id, name]);
  }

  scene_block { |scene_id, height|
    this.prSend("scene/name/block", [scene_id, height]);
  }

  scene_color { |scene_id, color|
    this.prSend("scene/color", [scene_id, color]);
  }

  scene_state { |scene_id|
    this.prSend("scene/state", [scene_id]);
  }

  scene_select { |scene_id|
    this.prSend("scene/select", [scene_id]);
  }

  // Tracks
  tracks {
    this.prSend("tracks");
  }

  trackPrSend { |action, args|
    this.prSend("track/%".format(action.asString), args);
  }

  track_arm { |track_id, state|
    this.trackPrSend("arm", [track_id, state]);
  }

  track_mute { |track_id, state|
    this.trackPrSend("mute", [track_id, state]);
  }

  track_solo { |track_id, state|
    this.trackPrSend("solo", [track_id, state]);
  }

  track_volume { |track_id, volume|
    this.trackPrSend("volume", [track_id, volume.asFloat]);
  }

  track_panning { |track_id, panning|
    this.trackPrSend("volume", [track_id, panning.asFloat]);
  }

  track_send { |track_id, send_id, value|
    this.trackPrSend("send", [track_id, send_id, value.asFloat]);
  }

  track_select { |track_id|
    this.trackPrSend("select", [track_id]);
  }

  track_crossfader { |track_id, state|
    this.trackPrSend("crossfader", [track_id, state]);
  }

  track_name { |track_id, name|
    this.trackPrSend("name", [track_id, name]);
  }

  track_color { |track_id, color|
    this.trackPrSend("color", [track_id, color]);
  }

  track_stop { |track_id, state|
    this.trackPrSend("stop", [track_id, state]);
  }

  track_state { |track_id, state|
    this.trackPrSend("state", [track_id, state]);
  }

  track_devices { |track_id, device_id|
    this.trackPrSend("devices", [track_id, device_id]);
  }

  track_device_range { |track_id, device_id, min, max|
    this.trackPrSend("device/range", [track_id, device_id, min, max]);
  }

  track_device_param { |track_id, device_id, value|
    this.trackPrSend("device/param", [track_id, device_id, value.asFloat]);
  }

  track_device_select { |track_id, device_id|
    this.trackPrSend("device/select", [track_id, device_id]);
  }

  // Return
  returnPrSend { |action, args|
    this.prSend("return/%".format(action.asString), args);
  }

  return_arm { |return_id, state|
    this.returnPrSend("arm", [return_id, state]);
  }

  return_mute { |return_id, state|
    this.returnPrSend("mute", [return_id, state]);
  }

  return_solo { |return_id, state|
    this.returnPrSend("solo", [return_id, state]);
  }

  return_volume { |return_id, volume|
    this.returnPrSend("volume", [return_id, volume.asFloat]);
  }

  return_panning { |return_id, panning|
    this.returnPrSend("volume", [return_id, panning.asFloat]);
  }

  return_send { |return_id, send_id, value|
    this.returnPrSend("send", [return_id, send_id, value.asFloat]);
  }

  return_select { |return_id|
    this.returnPrSend("select", [return_id]);
  }

  return_crossfader { |return_id, state|
    this.returnPrSend("crossfader", [return_id, state]);
  }

  return_name { |return_id, name|
    this.returnPrSend("name", [return_id, name]);
  }

  return_color { |return_id, color|
    this.returnPrSend("color", [return_id, color]);
  }

  return_stop { |return_id, state|
    this.returnPrSend("stop", [return_id, state]);
  }

  return_state { |return_id, state|
    this.returnPrSend("state", [return_id, state]);
  }

  return_devices { |return_id, device_id|
    this.returnPrSend("devices", [return_id, device_id]);
  }

  return_device_range { |return_id, device_id, min, max|
    this.returnPrSend("device/range", [return_id, device_id, min, max]);
  }

  return_device_param { |return_id, device_id, value|
    this.returnPrSend("device/param", [return_id, device_id, value.asFloat]);
  }

  return_device_select { |return_id, device_id|
    this.returnPrSend("device/select", [return_id, device_id]);
  }

  // Master
  masterPrSend { |action, args|
    this.prSend("master/%".format(action.asString), args);
  }

  master_arm { |state|
    this.masterPrSend("arm", [state]);
  }

  master_mute { |state|
    this.masterPrSend("mute", [state]);
  }

  master_solo { |state|
    this.masterPrSend("solo", [state]);
  }

  master_volume { |volume|
    this.masterPrSend("volume", [volume.asFloat]);
  }

  master_panning { |panning|
    this.masterPrSend("volume", [panning.asFloat]);
  }

  master_send { |send_id, value|
    this.masterPrSend("send", [send_id, value.asFloat]);
  }

  master_select {
    this.masterPrSend("select");
  }

  master_crossfader { |state|
    this.masterPrSend("crossfader", [state]);
  }

  master_devices { |device_id|
    this.masterPrSend("devices", [device_id]);
  }

  master_device_range { |device_id, min, max|
    this.masterPrSend("device/range", [device_id, min, max]);
  }

  master_device_param { |device_id, value|
    this.masterPrSend("device/param", [device_id, value.asFloat]);
  }

  master_device_select { |device_id|
    this.masterPrSend("device/select", [device_id]);
  }

  // Clips
  clipPrSend { |action, args|
    this.prSend("clip/%".format(action.asString), args);
  }

  clip_state { |track_id, scene_id|
    this.clipPrSend("state", [track_id, scene_id]);
  }

  clip_state_block { |track_id, scene_id, width, height|
    this.clipPrSend("state/block", [track_id, scene_id, width, height]);
  }

  clip_play { |track_id, scene_id|
    this.clipPrSend("play", [track_id, scene_id]);
  }

  clip_stop { |track_id, scene_id|
    this.clipPrSend("stop", [track_id, scene_id]);
  }

  clip_view { |track_id, scene_id|
    this.clipPrSend("view", [track_id, scene_id]);
  }

  clip_name { |track_id, scene_id|
    this.clipPrSend("name", [track_id, scene_id]);
  }

  clip_name_block { |track_id, scene_id, height, width|
    this.clipPrSend("name/block", [track_id, scene_id, height, width]);
  }

  clip_color { |track_id, scene_id|
    this.clipPrSend("color", [track_id, scene_id]);
  }

  clip_muted { |track_id, scene_id, muted|
    this.clipPrSend("muted", [track_id, scene_id, muted]);
  }

  clip_notes { |track_id, scene_id, start_time, start_pitch, time_length, pitch_length|
    this.clipPrSend("notes", [track_id, scene_id, start_time.asFloat, start_pitch, time_length.asFloat, pitch_length]);
  }

  clip_notes_add { |track_id, scene_id, pitch, time, duration, velocity, mute|
    /*
    pitch: 0 to 127
    time (in beats): 0=beat 1, 1=beat 2, 0.5 in between the two
    duration (in beats): 0.25 quarter beat, 0.5 half beat, 1 beat
    velocity: 0 to 127
    mute: 0 or 1
    */
    this.clipPrSend("notes/add", [track_id, scene_id, pitch, time.asFloat, duration.asFloat, velocity, mute]);
  }

  clip_notes_remove { |track_id, scene_id, start_time, start_pitch, time_length, pitch_length|
    this.clipPrSend("notes/remove", [track_id, scene_id, start_time.asFloat, start_pitch, time_length.asFloat, pitch_length]);
  }

  clip_looping { |track_id, scene_id|
    this.clipPrSend("looping", [track_id, scene_id]);
  }

  clip_loop_start { |track_id, scene_id|
    this.clipPrSend("loopstart", [track_id, scene_id]);
  }

  clip_loop_end { |track_id, scene_id|
    this.clipPrSend("loopend", [track_id, scene_id]);
  }

  clip_loop_jump { |track_id, scene_id|
    this.clipPrSend("loopjump", [track_id, scene_id]);
  }

  clip_start { |track_id, scene_id|
    this.clipPrSend("start", [track_id, scene_id]);
  }

  clip_end { |track_id, scene_id|
    this.clipPrSend("end", [track_id, scene_id]);
  }

  clip_warping { |track_id, scene_id|
    this.clipPrSend("warping", [track_id, scene_id]);
  }

  clip_pitch { |track_id, scene_id|
    this.clipPrSend("pitch", [track_id, scene_id]);
  }

  clip_create { |track_id, scene_id|
    this.clipPrSend("create", [track_id, scene_id]);
  }

  clip_delete { |track_id, scene_id|
    this.clipPrSend("delete", [track_id, scene_id]);
  }

  // Browser
  browserPrSend { |path, name|
    this.prSend("browser/%/load".format(path), name)
  }

  browser_drums_load { |name|
    this.browserPrSend("drums", [name]);
  }

  browser_instruments_load { |name|
    this.browserPrSend("instruments", [name]);
  }

  browser_audiofx_load { |name|
    this.browserPrSend("audiofx", [name]);
  }

  browser_midifx_load { |name|
    this.browserPrSend("midifx", [name]);
  }

  browser_m4l_load { |name|
    this.browserPrSend("m4l", [name]);
  }

  browser_plugins_load { |name|
    this.browserPrSend("plugins", [name]);
  }

  browser_clips_load { |name|
    this.browserPrSend("clips", [name]);
  }

  browser_samples_load { |name|
    this.browserPrSend("samples", [name]);
  }

  browser_packs_load { |name|
    this.browserPrSend("packs", [name]);
  }

  browser_userlib_load { |name|
    this.browserPrSend("userlib", [name]);
  }

  browser_currentprj_load { |name|
    this.browserPrSend("currentprj", [name]);
  }

  browser_userfolders_load { |name|
    this.browserPrSend("userfolders", [name]);
  }


}