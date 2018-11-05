# LiveOSC2.sc

Work in progress.
Note: Uses [my fork](https://github.com/lvm/LiveOSC2) of LiveOSC2.

## Install

`Quarks.install("https://github.com/lvm/LiveOSC2.sc");`

## Example

```
// LiveOSC2.sc is a SC Extension that talks to LiveOSC2 (an Ableton Control Surface)
l = LiveOSC2.new;

// and allows to change the tempo
l.tempo(136);

// mute tracks
l.track_mute(0, 0);
l.track_mute(1, 0);

// move around between tracks
l.track_select(1);

// load drumkits (or other instruments)
l.browser_load("505.adg");

// fiddle with Sends
l.track_send(1, 1, 0);

// and volume
l.track_volume(0, 0.65);
```

## License

See [LICENSE](LICENSE)