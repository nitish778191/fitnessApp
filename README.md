 <script>
      function getHuntEntries() {
        return {
          search: '',
          allHuntEntries: huntEntries,
          get filteredHuntEntries() {
            if (this.search === '') {
              return this.allHuntEntries;
            }
            return this.allHuntEntries.filter((entry) => {
              const searchTerm = this.search.replace(/ /g, '').toLowerCase();
              const title = entry.data.title.replace(/ /g, '').toLowerCase();
              const description = entry.data.description.replace(/ /g, '').toLowerCase();
              const hypothesis = entry.data.hypothesis.replace(/ /g, '').toLowerCase();

              return (
                title.includes(searchTerm) ||
                description.includes(searchTerm) ||
                hypothesis.includes(searchTerm)
              );
            });
          }
        };
      }

      // Sample hunt entries data
      const huntEntries = [
        {
          id: 'TH-0013',
          collection: 'hunts',
          data: {
            title: 'Sliver C2 Beacon Execution - MacOS',
            author: 'Paul Newton',
            creation_date: '2024/02/14',
            platform: 'EDR-Macos',
            attack_coverage: [{ technique: 'T1059' }, { technique: 'T1071' }],
            hypothesis:
              'Adversaries will attempt the execution of a payload to establish a C2. Sliver is a popular Open Source C2 framework.',
            description:
              'Modern C2 frameworks like Cobalt Strike and Sliver allow threat actors and Red Teamers to execute complex attacks...',
            hunter_notes: 'Review the application with the generic APP ID...',
          },
        },
        {
          id: 'TH-0014',
          collection: 'hunts',
          data: {
            title: 'Cobalt Strike Beacon Execution - Windows',
            author: 'Jane Doe',
            creation_date: '2024/03/22',
            platform: 'EDR-Windows',
            attack_coverage: [{ technique: 'T1080' }, { technique: 'T1095' }],
            hypothesis:
              'Cobalt Strike beacons allow attackers to maintain long-term access to a compromised system.',
            description:
              'Cobalt Strike is widely used in attacks, leveraging various exploitation techniques to remain persistent...',
            hunter_notes: 'Analyze network traffic for unusual beacon activity...',
          },
        },
        // More entries can be added here
      ];
    </script>
