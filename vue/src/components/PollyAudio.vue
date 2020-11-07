<template>
    <div>
      <play-observer @no-intersect="pause" @intersect="play"></play-observer>
    </div>
</template>

<script>
import axios from 'axios'
import AWS from 'aws-sdk'
import PlayObserver from './PollyPlayObserver';

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
  },
  props: {
    text: {
      type: String,
    }
  },
  methods: {

    play: function() {
      this.audioContext = new (window.AudioContext || window.webkitAudioContext)();
      this.gainNode = this.audioContext.createGain();

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
      if (typeof this.gainNode !== 'undefined') {
        this.gainNode.gain.exponentialRampToValueAtTime(0.000001, 0.6);
        window.setTimeout(this.suspend, 1800);
      }
    },

    suspend: function() {
      this.audioContext.suspend();
    },

    playAudio: function() {
      const sourceUrl = this.genUrl;
      console.log(sourceUrl);
      const sourceNode = this.audioContext.createBufferSource();
      
      this.setupAudio(sourceUrl, sourceNode)
      .then(this.gainNode.gain.setValueAtTime(0.00001, 0))
      .then(sourceNode.connect(this.createReverb()).connect(this.gainNode)).then(sourceNode.start())
      .then(this.gainNode.gain.exponentialRampToValueAtTime(1, 0.3));

    },
    createGain: function() {
      this.gainNode.gain.setValueAtTime(0.00001, 0);
      return this.gainNode;
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