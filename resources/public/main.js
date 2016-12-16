window.Reveal.initialize({
  history: true,
  dependencies: [
    {
      src: '/reveal/plugin/highlight/highlight.js',
      async: true,
      callback: function () { window.hljs.initHighlightingOnLoad() }
    }
  ]
})
