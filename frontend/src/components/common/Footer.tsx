const Footer = () => {
  return (
    <footer
      style={{
        position: 'relative', 
        marginTop: 'auto',    
        width: '100%',
        height: '80px',
        backgroundColor: 'black',
        color: 'white',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        zIndex: 10,
      }}
    >
      <span className="text-lg font-semibold transition-all duration-300 hover:scale-110 hover:text-violet-400">
        Synergym
      </span>
    </footer>
  );
};

export default Footer;
