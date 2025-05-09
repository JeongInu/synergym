const App = () => {
  const sections = [
    { bg: 'bg-red-500', text: 'Section 1' },
    { bg: 'bg-blue-500', text: 'Section 2' },
    { bg: 'bg-green-500', text: 'Section 3' },
    { bg: 'bg-yellow-500', text: 'Section 4' },
    { bg: 'bg-purple-500', text: 'Section 5' },
  ];

  return (
    <div className="overflow-x-hidden">
      {sections.map((sec, index) => (
        <section
          key={index}
          className={`w-full h-screen flex items-center justify-center ${sec.bg}`}
        >
          <div className="text-white text-6xl font-bold">
            {sec.text}
          </div>
        </section>
      ))}
    </div>
  );
};

export default App;
