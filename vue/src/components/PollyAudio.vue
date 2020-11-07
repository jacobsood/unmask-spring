<template>
    <div>
      <play-observer @intersect="play"></play-observer>
      <stop-observer @intersect="pause"></stop-observer>
    </div>
</template>

<script>
import axios from 'axios'
import AWS from 'aws-sdk'
import PlayObserver from './PollyPlayObserver';
import StopObserver from './PollyStopObserver';

export default {
  name: 'PollyAudio',
  data: function() {
    return {
      playDelay: 800,
      reverbIndex: 0,
      audioContext: undefined,
      reverbSamples: [ 
        'https://hrithviksood.s3-ap-southeast-2.amazonaws.com/unmask/audio/ReverbSample/customReverb.wav',
        'https://hrithviksood.s3-ap-southeast-2.amazonaws.com/unmask/audio/ReverbSample/customReverb2.wav',
      ],
      polly: new AWS.Polly({apiVersion: '2016-06-10'}),
      speechParams: {
        OutputFormat: "mp3",
        SampleRate: "16000",
        Text: this.text,
        TextType: "text",
        VoiceId: "Matthew"
      },
      genUrl: undefined,
      gainNode: undefined,
    };
  },
  components: {
    PlayObserver,
    StopObserver
  },
  props: {
    text: {
      type: String,
    }
  },
  methods: {

    play: function() {
      if (typeof this.audioContext !== 'undefined') {
        this.audioContext.resume();
        return;
      }

      this.audioContext = this.createAudioCtx();
      this.createListener();

      // Initialize the Amazon Cognito credentials provider
      AWS.config.region = 'ap-southeast-2'; 
      AWS.config.credentials = new AWS.CognitoIdentityCredentials({IdentityPoolId: 'ap-southeast-2:54e6603d-a856-4480-990b-5600925b04fb'});

      var polly_signer = new AWS.Polly.Presigner(this.speechParams, this.polly);
      polly_signer.getSynthesizeSpeechUrl(this.speechParams, (error, url) => {
        this.genUrl = url;
        console.log(url);
        window.setTimeout(this.playAudio, this.playDelay);
      });
    },

    pause: function() {
      this.gainNode.gain.exponentialRampToValueAtTime(0.00001, 1.3);
      // this.audioContext.suspend();
    },
    
    createListener: function() {
      const listener = this.audioContext.listener;
      const posX = 0;
      const posY = 0;
      const posZ = 0;
      listener.setPosition(posX, posY, posZ);
      
      listener.forwardX.value = 0;
      listener.forwardY.value = 0;
      listener.forwardZ.value = 1;
      listener.upX.value = 0;
      listener.upY.value = -1;
      listener.upZ.value = 0;
    },
    createAudioCtx: function() {
      const AudioContext = window.AudioContext || window.webkitAudioContext;
      return new AudioContext();
    },

    playAudio: function() {
      const sourceUrl = this.genUrl;
      console.log(sourceUrl);
      const sourceNode = this.audioContext.createBufferSource();
      
      this.setupAudio(sourceUrl, sourceNode)
      .then(sourceNode.connect(this.createGain()).connect(this.createReverb())).then(sourceNode.start())
      .then(this.gainNode.gain.exponentialRampToValueAtTime(1, 0.3));
    },
    createGain: function() {
      const gainNode = this.audioContext.createGain();
      gainNode.gain.setValueAtTime(0.00001, 0);
      this.gainNode = gainNode;
      return gainNode;
    },
    createReverb: function() {
      const index = Math.floor(Math.random() * this.sampleSize);
      var reverbUrl = this.reverbSamples[index];
      const reverbNode = this.audioContext.createConvolver();
      this.setupAudio(reverbUrl, reverbNode).then(reverbNode.connect(this.audioContext.destination));
      return reverbNode;
    },
    setupAudio: async function(url, node) {
      axios.get(url, { responseType: 'arraybuffer' }).then(response => {
        this.audioContext.decodeAudioData(response.data, audioBuffer => {
          node.buffer = audioBuffer;
        })
      });
    }
  },

  computed: {
    sampleSize: function() {
      return this.reverbSamples.length;
    },
  },
}
</script>